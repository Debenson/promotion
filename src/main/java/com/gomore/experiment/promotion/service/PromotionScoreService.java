/**
 * 版权所有(C)，上海勾芒信息科技，2018，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	ScoreService.java
 * 模块说明：	
 * 修改历史：
 * 2018年3月15日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.service;

import java.math.BigDecimal;

import com.gomore.experiment.promotion.model.action.NScoreAction;
import com.gomore.experiment.promotion.model.action.ScoreAction;
import com.gomore.experiment.promotion.service.bean.OrderBill;

/**
 * 积分服务
 * 
 * @author Debenson
 * @since 0.1
 */
public interface PromotionScoreService {

  /**
   * 调整会员积分
   * 
   * @param bill
   *          业务单据，非空
   * @param action
   *          促销事件，非空
   */
  void adjustScore(OrderBill bill, ScoreAction action);

  /**
   * 调整会员翻倍积分
   * 
   * @param bill
   *          业务单据，非空
   * @param extraScore
   *          发生的积分
   * @param action
   *          促销事件，非空
   */
  void adjustScore(OrderBill bill, BigDecimal extraScore, NScoreAction action);

}
