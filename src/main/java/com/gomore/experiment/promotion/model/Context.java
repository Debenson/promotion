/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	PromotionContext.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月14日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model;

import java.io.Serializable;
import java.util.Map;

import com.gomore.experiment.promotion.engine.ConditionResult;
import com.google.common.collect.Maps;

/**
 * 促销上下文。
 * 
 * @author Debenson
 * @since 0.1
 */
public class Context implements Serializable {
  private static final long serialVersionUID = 1286498646911116647L;

  /** 环境变量：订单 */
  public static final String KEY_ORDER = "order";
  /** 环境变量：订单标识 */
  public static final String KEY_ORDER_ID = "orderId";
  /** 环境变量：订单详情 */
  public static final String KEY_ORDER_DTLS = "orderDtls";
  /** 环境变量: 计算结果暂存 */
  public static final String KEY_CALC_TRACE = "calcTrace";

  private Map<String, Object> context = Maps.newConcurrentMap();

  public static Context newInstance() {
    Context context = new Context();
    return context;
  }

  public Map<String, Object> getContext() {
    return context;
  }

  public boolean containsKey(Object key) {
    return context.containsKey(key);
  }

  public Object put(String key, Object value) {
    if (value != null) {
      return context.put(key, value);
    } else {
      return null;
    }
  }

  public Object get(Object key) {
    return context.get(key);
  }

  /**
   * 添加计算结果跟踪
   * 
   * @param key
   *          一般为条件或表达式
   * @param result
   *          条件或表达式的计算结果
   */
  @SuppressWarnings("unchecked")
  public void addTrace(Object key, ConditionResult result) {
    Map<Object, ConditionResult> traceMap = (Map<Object, ConditionResult>) context
        .get(KEY_CALC_TRACE);
    if (traceMap == null) {
      traceMap = Maps.newHashMap();
      context.put(KEY_CALC_TRACE, traceMap);
    }
    traceMap.put(key, result);
  }

  /**
   * 取得计算结果
   * 
   * @param key
   *          一般为条件或表达式
   * @return
   */
  @SuppressWarnings("unchecked")
  public ConditionResult getTrace(Object key) {
    Map<Object, ConditionResult> traceMap = (Map<Object, ConditionResult>) context
        .get(KEY_CALC_TRACE);
    return traceMap == null ? null : traceMap.get(key);
  }

}
