/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	CaculatorSupport.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月23日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine.calculator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.gomore.experiment.promotion.common.HasUCN;
import com.gomore.experiment.promotion.engine.Caculator;
import com.gomore.experiment.promotion.model.Context;
import com.gomore.experiment.promotion.model.PromotionException;
import com.gomore.experiment.promotion.model.condition.Condition;
import com.gomore.experiment.promotion.model.condition.event.PromotionEvent;
import com.gomore.experiment.promotion.model.condition.step.Stair;
import com.gomore.experiment.promotion.model.condition.step.StepInfo;
import com.gomore.experiment.promotion.model.condition.step.StepType;
import com.gomore.experiment.promotion.service.PromotionOrderBillService;
import com.gomore.experiment.promotion.service.bean.Member;
import com.gomore.experiment.promotion.service.bean.OrderBill;
import com.gomore.experiment.promotion.service.bean.OrderBillGoodsDetail;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * 促销计算器基类
 * 
 * @author Debenson
 * @since 0.1
 */
public abstract class CaculatorSupport<T extends Condition> implements Caculator<T> {

  @Autowired
  private PromotionOrderBillService orderService;

  private List<Class<T>> acceptClazz;

  @SafeVarargs
  public CaculatorSupport(Class<T>... clazz) {
    acceptClazz = Lists.newArrayList(clazz);
  }

  @Override
  public boolean support(Condition condition) {
    for (Class<T> clazz : acceptClazz) {
      if (condition.getClass().equals(clazz)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 取得促销事件
   * 
   * @param context
   * @return
   */
  protected PromotionEvent getEvent(Context context) {
    OrderBill order = getOrder(context);
    return order == null ? null : order.getEvent();
  }

  /**
   * 取得促销会员
   * 
   * @param context
   * @return
   */
  protected Member getMember(Context context) {
    OrderBill order = getOrder(context);
    if (order == null) {
      return null;
    } else {
      return (Member) order.getConsumer();
    }
  }

  /**
   * 取得交易时间， 如果没有设置， 则初始为当前时间并返回。
   * 
   * @return
   */
  protected Date safeGetTradeTime(Context context) {
    OrderBill order = getOrder(context);
    return order == null ? null : order.getTradeTime();
  }

  /**
   * 取得订单。
   * 
   * @param context
   * @return
   */
  protected synchronized OrderBill getOrder(Context context) {
    OrderBill order = (OrderBill) context.get(Context.KEY_ORDER);
    if (order == null) {
      final String orderId = (String) context.get(Context.KEY_ORDER_ID);
      if (StringUtils.isNotBlank(orderId)) {
        order = orderService.getOrderById(orderId);
        context.put(Context.KEY_ORDER, order);
      }
    }
    if (order == null) {
      throw new PromotionException("找不到订单");
    }
    return order;
  }

  /**
   * 取商品明细。
   * 
   * @param context
   * @return
   */
  @SuppressWarnings("unchecked")
  protected synchronized List<OrderBillGoodsDetail> getOrderDtls(Context context) {
    List<OrderBillGoodsDetail> dtls = (List<OrderBillGoodsDetail>) context
        .get(Context.KEY_ORDER_DTLS);
    if (dtls != null) {
      return dtls;
    }

    OrderBill order = getOrder(context);
    if (order != null) {
      dtls = order.getGoodsDetails();
    }

    if (dtls != null) {
      context.put(Context.KEY_ORDER_DTLS, dtls);
      return dtls;
    } else {
      return Lists.newArrayList();
    }
  }

  /**
   * 取订单商品标识列表。
   * 
   * @param context
   * @return
   */
  protected List<String> safeGetGoodsIds(Context context) {
    List<OrderBillGoodsDetail> dtls = getOrderDtls(context);
    Set<String> goodsIds = Sets.newHashSet();
    for (OrderBillGoodsDetail dtl : dtls) {
      goodsIds.add(dtl.getGoods().getUuid());
    }
    return Lists.newArrayList(goodsIds);
  }

  /**
   * 取订单商品+数量。
   * 
   * @param context
   * @return
   */
  protected Map<String, Integer> safeGetGoodsCountMap(Context context) {
    List<OrderBillGoodsDetail> dtls = getOrderDtls(context);
    Map<String, Integer> map = Maps.newHashMap();
    for (OrderBillGoodsDetail dtl : dtls) {
      final String goodsId = dtl.getGoods().getUuid();
      if (!map.containsKey(goodsId)) {
        map.put(goodsId, 0);
      }
      int count = map.get(goodsId);
      map.put(goodsId, count + dtl.getCount());
    }
    return map;
  }

  /**
   * 取订单商品+金额。
   * 
   * @param context
   * @return
   */
  protected synchronized Map<String, BigDecimal> safeGetGoodsAmountMap(Context context) {
    List<OrderBillGoodsDetail> dtls = getOrderDtls(context);
    Map<String, BigDecimal> map = Maps.newHashMap();
    for (OrderBillGoodsDetail dtl : dtls) {
      final String goodsId = dtl.getGoods().getUuid();
      if (!map.containsKey(goodsId)) {
        map.put(goodsId, BigDecimal.ZERO);
      }
      BigDecimal total = map.get(goodsId);
      BigDecimal goodsCount = new BigDecimal(dtl.getCount());
      map.put(goodsId, total.add(dtl.getPrice().multiply(goodsCount)));
    }
    return map;
  }

  /**
   * 取订单商品品牌列表。
   * 
   * @param context
   * @return
   */
  protected synchronized List<String> safeGetGoodsBrands(Context context) {
    Set<String> brands = Sets.newHashSet();
    List<OrderBillGoodsDetail> dtls = getOrderDtls(context);
    for (OrderBillGoodsDetail dtl : dtls) {
      brands.add(dtl.getBrand().getUuid());
    }
    return Lists.newArrayList(brands);
  }

  /**
   * 取订单商品分类列表。
   * 
   * @param context
   * @return
   */
  protected synchronized List<HasUCN> safeGetGoodsCategories(Context context) {
    Map<String, HasUCN> catMaps = Maps.newHashMap();
    List<OrderBillGoodsDetail> dtls = getOrderDtls(context);
    for (OrderBillGoodsDetail dtl : dtls) {
      if (dtl.getCategories() != null) {
        for (HasUCN cat : dtl.getCategories()) {
          catMaps.put(cat.getUuid(), cat);
        }
      }
    }
    return Lists.newArrayList(catMaps.values());
  }

  /**
   * 计算步进。
   * 
   * @param orderValue
   *          订单上的值 。
   * @param conditionValue
   *          促销条件定义的值。
   * @param stepInfo
   *          步进信息。
   * @return
   */
  protected BigDecimal calcStep(BigDecimal orderValue, BigDecimal conditionValue,
      StepInfo stepInfo) {
    BigDecimal step = null;
    if (StepType.STEP.equals(stepInfo.getType())) {
      // 等比步进
      int stepCount = orderValue.divide(conditionValue).intValue();
      step = BigDecimal.valueOf(stepCount);

    } else if (StepType.STAIR.equals(stepInfo.getType())) {
      // 阶梯步进
      for (Stair stair : stepInfo.getStairSteps()) {
        boolean b = AlgorithmCalculator.isBetween(orderValue, stair.getFrom(), stair.getTo());
        if (b) {
          step = stair.getValue();
          break;
        }
      }
    }
    return step;
  }

}
