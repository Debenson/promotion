package com.gomore.experiment.promotion.service;

import com.gomore.experiment.promotion.engine.PromotionResult;
import com.gomore.experiment.promotion.service.bean.OrderBill;

/**
 * <p>
 * 促销报表 服务类
 * </p>
 *
 * @author tom
 * @since 2018-04-09
 */
public interface PromotionResultService {

  /**
   * 保存促销记录
   * 
   * @param bill
   *          促销订单
   * @param promResult
   *          促销计算结果
   */
  void savePromotionRecord(OrderBill bill, PromotionResult promResult);

}
