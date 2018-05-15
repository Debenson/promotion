/**
 * 版权所有(C)，上海勾芒信息科技，2017，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	AutoActionExecutor.java
 * 模块说明：	
 * 修改历史：
 * 2017年9月5日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.support;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gomore.experiment.commons.exception.ServiceException;
import com.gomore.experiment.promotion.engine.PromotionEngine;
import com.gomore.experiment.promotion.engine.PromotionResult;
import com.gomore.experiment.promotion.model.PromotionException;
import com.gomore.experiment.promotion.model.action.Action;
import com.gomore.experiment.promotion.model.action.CouponAction;
import com.gomore.experiment.promotion.model.action.NScoreAction;
import com.gomore.experiment.promotion.model.action.PrizeAction;
import com.gomore.experiment.promotion.model.action.ScoreAction;
import com.gomore.experiment.promotion.service.PromotionCouponService;
import com.gomore.experiment.promotion.service.PromotionPrizeService;
import com.gomore.experiment.promotion.service.PromotionScoreService;
import com.gomore.experiment.promotion.service.bean.OrderBill;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * 促销结果的自动执行者 <br>
 * 促销结果一般分两种：一种是可自动执行的， 一种是需要用户参与才能执行的。这里只处理可执行的促销结果。
 * 
 * @author Debenson
 * @since 0.1
 */
@Component
@Slf4j
public class ActionAutoExecutor {
  static final Logger logger = LoggerFactory.getLogger(ActionAutoExecutor.class);

  @Autowired
  private PromotionEngine engine;
  @Autowired
  private PromotionCouponService couponService;
  @Autowired
  private PromotionPrizeService prizeService;
  @Autowired
  private PromotionScoreService scoreService;

  /**
   * 预计算促销结果，实际不会执行促销结果。
   * 
   * @param bill
   *          促销订单
   * @return 促销结果
   * @throws PromotionException
   * @see {@link #execute(OrderBill)}
   */
  public PromotionResult preExecute(OrderBill bill) throws PromotionException {
    // 计算促销结果
    PromotionResult result = engine.execute(bill);
    return result;
  }

  /**
   * 自动执行促销结果 <br>
   * 不能执行的促销结果将被忽略。
   * 
   * @param bill
   *          促销订单
   * @return 促销结果
   * @throws PromotionException
   */
  public PromotionResult execute(OrderBill bill) throws PromotionException {
    // 计算促销结果
    final PromotionResult result = preExecute(bill);

    // 已处理的促销结果
    List<NScoreAction> nscoreActions = Lists.newArrayList();
    BigDecimal scoreSum = BigDecimal.ZERO;
    for (Action action : result.getActions()) {
      if (CouponAction.class.equals(action.getClass())) {
        try {
          processCouponAction(bill, (CouponAction) action);
        } catch (Exception e) {
          log.error("促销送券失败", e);
        }

      } else if (PrizeAction.class.equals(action.getClass())) {
        try {
          processPrizeAction(bill, (PrizeAction) action);
        } catch (Exception e) {
          log.error("促销券奖品失败", e);
        }

      } else if (ScoreAction.class.equals(action.getClass())) {
        try {
          processScoreAction(bill, (ScoreAction) action);
        } catch (Exception e) {
          log.error("促销送积分失败", e);
        }

        // 总积分
        scoreSum = scoreSum.add(((ScoreAction) action).getTotal());
      } else if (NScoreAction.class.equals(action.getClass())) {
        nscoreActions.add((NScoreAction) action);

      } else {
        logger.warn("忽略了促销结果: {}", action.getClass().getName());
      }
    }
    if (!nscoreActions.isEmpty()) {
      try {
        processNScoreAction(bill, scoreSum, nscoreActions);
      } catch (Exception e) {
        log.error("促销送翻倍积分失败", e);
      }
    }
    return result;
  }

  /**
   * 处理送券
   * 
   * @param bill
   * @param action
   * @throws PromotionException
   */
  protected void processCouponAction(OrderBill bill, CouponAction action)
      throws PromotionException {
    logger.info("正在执行卡券促销结果");
    for (int i = 0; i < action.getCount(); ++i) {
      try {
        couponService.makeAndAssignCoupon(bill, action);
      } catch (ServiceException e) {
        log.error("", e);
      }
    }
  }

  /**
   * 处理送奖品
   * 
   * @param bill
   * @param action
   * @throws PromotionException
   */
  protected void processPrizeAction(OrderBill bill, PrizeAction action) throws PromotionException {
    logger.info("正在执行奖品促销结果");

    for (int i = 0; i < action.getCount(); ++i) {
      prizeService.givePrize(bill, action);
    }
  }

  /**
   * 处理送积分
   * 
   * @param bill
   * @param action
   *          积分促销结果
   * @param nscoreAction
   *          翻倍积分促销结果，如果没有可以传null
   * @throws PromotionException
   */
  protected void processScoreAction(OrderBill bill, ScoreAction action) throws PromotionException {
    logger.info("正在执行积分促销结果");
    if (action.getTotal() == null || action.getTotal().compareTo(BigDecimal.ZERO) <= 0) {
      logger.warn("积分促销结果的积分值为0，请修改促销单");
      return;
    }

    scoreService.adjustScore(bill, action);
  }

  /**
   * 处理翻倍积分
   * 
   * @param bill
   * @param score
   *          总积分
   * @param actions
   *          翻倍积分促销条件
   * @throws PromotionException
   */
  protected void processNScoreAction(OrderBill bill, BigDecimal score, List<NScoreAction> actions)
      throws PromotionException {
    if (score == null || score.compareTo(BigDecimal.ZERO) <= 0 || actions.isEmpty()) {
      // 没有促销积分或没有翻倍积分促销结果，就不用再计算翻倍积分了
      return;
    }

    // 计算翻倍系数，简单处理：只取最大的，不累加翻位系数
    BigDecimal times = BigDecimal.ONE;
    NScoreAction effectiveAction = null;
    for (NScoreAction action : actions) {
      if (action.getTotal() != null && action.getTotal().compareTo(times) > 0) {
        times = action.getTotal();
        effectiveAction = action;
      }
    }

    if (effectiveAction == null) {
      logger.warn("没有有效的翻倍积分促销结果，请检查促销单");
      return;
    }

    final BigDecimal extraScore = times.subtract(BigDecimal.ONE).multiply(score);
    if (extraScore.compareTo(BigDecimal.ZERO) > 0) {
      scoreService.adjustScore(bill, extraScore, effectiveAction);
    }
  }

}
