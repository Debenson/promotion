/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	BasketCaculator.java
 * 模块说明：	
 * 修改历史：
 * 2016年11月1日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine.calculator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gomore.experiment.promotion.engine.ConditionResult;
import com.gomore.experiment.promotion.engine.IsCaculator;
import com.gomore.experiment.promotion.engine.PromotionEngine;
import com.gomore.experiment.promotion.model.Context;
import com.gomore.experiment.promotion.model.condition.basket.BasketCondition;
import com.gomore.experiment.promotion.service.bean.OrderBillGoodsDetail;
import com.google.common.collect.Lists;

/**
 * 购物篮促销条件计算器。
 * 
 * @author Debenson
 * @since 0.1
 */
@IsCaculator
public class BasketCaculator extends CaculatorSupport<BasketCondition> {
  static final Logger logger = LoggerFactory.getLogger(BasketCaculator.class);

  @Autowired
  private PromotionEngine engine;

  public BasketCaculator() {
    super(BasketCondition.class);
  }

  @Override
  public ConditionResult accept(BasketCondition condition, Context context) {
    final List<OrderBillGoodsDetail> goodsDtls = getOrderDtls(context);

    // 先计算出满足商品条件的订单明细行。
    List<OrderBillGoodsDetail> acceptDtls = Lists.newArrayList();
    for (OrderBillGoodsDetail dtl : goodsDtls) {
      ConditionResult r = engine.accept(condition.goodsCondition(),
          BasketContext.create(context, dtl));
      if (r.isAccept()) {
        acceptDtls.add(dtl);
      }
    }

    // 如果没有满足的商品行，则返回false。
    if (acceptDtls.isEmpty()) {
      return ConditionResult.accept(false);
    }

    // 再次计算一下购物蓝条件中的商品条件的最终计算结果，这里主要为了方便跟踪促销计算过程
    final BasketContext basketContext = BasketContext.create(context,
        acceptDtls.toArray(new OrderBillGoodsDetail[0]));
    engine.accept(condition.goodsCondition(), basketContext); // 忽略计算结果，因为这里必定是接受商品条件的

    // 计算购物篮的统计条件。
    return engine.accept(condition.statisticsCondition(), basketContext);
  }

  /**
   * 购物篮上下文环境。
   * 
   * @author Debenson
   * @since 0.1
   */
  static class BasketContext extends Context {
    private static final long serialVersionUID = 3876619834869329512L;

    private Context parentContext;

    public static BasketContext create(Context context, OrderBillGoodsDetail... dtls) {
      BasketContext basketContext = new BasketContext(context);
      basketContext.put(KEY_ORDER_DTLS, Lists.newArrayList(dtls));
      return basketContext;
    }

    public BasketContext(Context parentContext) {
      super();
      this.parentContext = parentContext;
      getContext().putAll(parentContext.getContext());
    }

    public Context getParentContext() {
      return parentContext;
    }

    public void setParentContext(Context parentContext) {
      this.parentContext = parentContext;
    }

  }

}
