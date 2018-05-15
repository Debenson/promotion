/**
 * 版权所有(C)，上海勾芒信息科技，2017，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	SimplePromotionResult.java
 * 模块说明：	
 * 修改历史：
 * 2017年10月16日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.gomore.experiment.promotion.bill.bean.PromotionBill;
import com.gomore.experiment.promotion.engine.PromotionResult;
import com.gomore.experiment.promotion.model.Context;
import com.gomore.experiment.promotion.model.action.Action;
import com.gomore.experiment.promotion.model.action.CouponAction;
import com.gomore.experiment.promotion.model.action.DeductionAction;
import com.gomore.experiment.promotion.model.action.DiscountAction;
import com.gomore.experiment.promotion.model.action.GoodsAction;
import com.gomore.experiment.promotion.model.action.NScoreAction;
import com.gomore.experiment.promotion.model.action.PrizeAction;
import com.gomore.experiment.promotion.model.action.ScoreAction;
import com.gomore.experiment.promotion.model.action.UseCouponAction;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 促销结果构造器
 * 
 * @author Debenson
 * @since 0.1
 */
public class PromotionResultBuilder {

  private SimplePromotionResult result;

  public PromotionResultBuilder() {
    result = new SimplePromotionResult();
  }

  public PromotionResult build() {
    result.setDescription(generateDescription());
    result.setScore(calcSumScore());
    return result;
  }

  public PromotionResultBuilder actions(List<Action> actions) {
    result.setActions(actions);
    return this;
  }

  public PromotionResultBuilder context(Context context) {
    result.setContext(context);
    return this;
  }

  public PromotionResultBuilder effectiveBills(List<PromotionBill> effectiveBills) {
    result.setEffectiveBills(effectiveBills);
    return this;
  }

  /**
   * 生成 促销的描述
   * 
   * @return
   */
  private String generateDescription() {
    StringBuffer sb = new StringBuffer();
    Map<String, List<Action>> group = result.getGroupActions();
    for (Entry<String, List<Action>> entry : group.entrySet()) {
      if (DeductionAction.class.getName().equals(entry.getKey())) {
        genDeduction(entry.getValue(), sb);

      } else if (DiscountAction.class.getName().equals(entry.getKey())) {
        genDiscount(entry.getValue(), sb);

      } else if (GoodsAction.class.getName().equals(entry.getKey())) {
        genGoods(entry.getValue(), sb);

      } else if (ScoreAction.class.getName().equals(entry.getKey())) {
        genScore(entry.getValue(), group.get(NScoreAction.class.getName()), sb);

      } else if (CouponAction.class.getName().equals(entry.getKey())) {
        genCoupon(entry.getValue(), sb);

      } else if (PrizeAction.class.getName().equals(entry.getKey())) {
        genPrize(entry.getValue(), sb);

      } else if (UseCouponAction.class.getName().equals(entry.getKey())) {
        // 券支付优惠就不再显示了
        // genUseCoupon(entry.getValue(), sb);
      }
    }
    return sb.toString().trim();
  }

  // private void genUseCoupon(List<Action> actions, StringBuffer sb) {
  // sb.append("赠送券支付优惠: ");
  // for (int i = 0; i < actions.size(); ++i) {
  // UseCouponAction useCouponAction = (UseCouponAction) actions.get(i);
  // if (i != 0) {
  // sb.append(",");
  // }
  // sb.append(useCouponAction.getCount() + " 张 ")
  // .append(useCouponAction.getActivity().toFriendlyString());
  // }
  // sb.append("\n");
  // }

  private void genPrize(List<Action> actions, StringBuffer sb) {
    sb.append("赠送奖品: ");
    for (int i = 0; i < actions.size(); ++i) {
      PrizeAction prizeAction = (PrizeAction) actions.get(i);
      if (i != 0) {
        sb.append(",");
      }
      sb.append(prizeAction.getCount()).append(" 件 ")
          .append(prizeAction.getPrize().toFriendlyString());
    }
    sb.append("\n");
  }

  private void genCoupon(List<Action> actions, StringBuffer sb) {
    sb.append("赠送券: ");
    for (int i = 0; i < actions.size(); ++i) {
      CouponAction couponAction = (CouponAction) actions.get(i);
      if (i != 0) {
        sb.append(",");
      }
      sb.append(couponAction.getCount() + " 张 ")
          .append(couponAction.getActivity().toFriendlyString());
    }
    sb.append("\n");
  }

  private void genScore(List<Action> actions, List<Action> nscoreActions, StringBuffer sb) {
    final BigDecimal times = getScoreTimes(nscoreActions);
    BigDecimal total = BigDecimal.ZERO;
    for (Action action : actions) {
      total = total.add(((ScoreAction) action).getTotal());
    }
    sb.append("赠送积分: ").append(total.multiply(times).setScale(2, BigDecimal.ROUND_HALF_UP))
        .append("\n");
  }

  private void genDeduction(List<Action> actions, StringBuffer sb) {
    BigDecimal total = BigDecimal.ZERO;
    for (Action action : actions) {
      total = total.add(((DeductionAction) action).getTotal());
    }
    sb.append("赠送整单抵扣: ").append(total).append("元").append("\n");
  }

  private void genDiscount(List<Action> actions, StringBuffer sb) {
    BigDecimal discount = new BigDecimal(100);
    for (Action action : actions) {
      discount = discount.multiply(((DiscountAction) action).getTotal())
          .divide(new BigDecimal(100));
    }
    sb.append("赠送整单折扣: ")
        .append(discount.divide(new BigDecimal(10)).setScale(1, BigDecimal.ROUND_HALF_UP))
        .append("折\n");
  }

  private void genGoods(List<Action> actions, StringBuffer sb) {
    sb.append("赠送商品: ");
    for (int i = 0; i < actions.size(); ++i) {
      GoodsAction goodsAction = (GoodsAction) actions.get(i);
      if (i != 0) {
        sb.append(",");
      }
      sb.append(goodsAction.getCount()).append("件")
          .append(goodsAction.getGoods().toFriendlyString());
    }
    sb.append("\n");
  }

  /**
   * 计算促销结果的总积分
   * 
   * @param actions
   * @return
   */
  private BigDecimal calcSumScore() {
    BigDecimal score = BigDecimal.ZERO;

    List<Action> nscoreActions = Lists.newArrayList();
    for (Action action : result.getActions()) {
      if (action instanceof ScoreAction) {
        score = score.add(((ScoreAction) action).getTotal());
      }
      if (action instanceof NScoreAction) {
        nscoreActions.add((NScoreAction) action);
      }
    }
    final BigDecimal scoreTimes = getScoreTimes(nscoreActions);
    return score.multiply(scoreTimes);
  }

  /**
   * 计算翻倍系数
   * 
   * @param nscoreActions
   * @return
   */
  private BigDecimal getScoreTimes(List<Action> nscoreActions) {
    BigDecimal times = BigDecimal.ONE;
    if (nscoreActions != null) {
      for (Action action : nscoreActions) {
        NScoreAction nscoreAction = (NScoreAction) action;
        if (nscoreAction.getTotal() != null && nscoreAction.getTotal().compareTo(times) > 0) {
          times = nscoreAction.getTotal();
        }
      }
    }
    return times;
  }

  /**
   * @author Debenson
   * @since 0.1
   */
  static class SimplePromotionResult implements PromotionResult {
    private Context context;
    private List<Action> actions = Lists.newArrayList();
    private List<PromotionBill> effectiveBills = Lists.newArrayList();
    private String description;
    private BigDecimal score = BigDecimal.ZERO;
    private Map<String, List<Action>> groupActions = Maps.newHashMap();

    @Override
    public List<Action> getActions() {
      return actions;
    }

    public void setActions(List<Action> actions) {
      this.actions = actions;
      this.groupActions = getGroupActions(actions);
    }

    @Override
    public Context getContext() {
      return context;
    }

    public void setContext(Context context) {
      this.context = context;
    }

    @Override
    public List<PromotionBill> getEffectiveBills() {
      return effectiveBills;
    }

    public void setEffectiveBills(List<PromotionBill> effectiveBills) {
      this.effectiveBills = effectiveBills;
    }

    @Override
    public String toString() {
      return description;
    }

    @Override
    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    @Override
    public BigDecimal getScore() {
      return score;
    }

    public void setScore(BigDecimal score) {
      this.score = score;
    }

    @Override
    public List<Action> getActions(Class<? extends Action> clazz) {
      if (groupActions.containsKey(clazz.getName())) {
        return groupActions.get(clazz.getName());
      } else {
        return Lists.newArrayList();
      }
    }

    public Map<String, List<Action>> getGroupActions() {
      return groupActions;
    }

    private Map<String, List<Action>> getGroupActions(List<Action> actions) {
      Map<String, List<Action>> group = Maps.newHashMap();
      for (Action action : actions) {
        String key = action.getClass().getName();
        if (!group.containsKey(key)) {
          group.put(key, Lists.newArrayList(action));
        } else {
          group.get(key).add(action);
        }
      }
      return group;
    }

  }

}
