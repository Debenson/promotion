/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	OrderBillService.java
 * 模块说明：	
 * 修改历史：
 * 2016年11月7日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.service;

import com.gomore.experiment.promotion.service.bean.OrderBill;

/**
 * 订单服务接口。
 * 
 * @author Debenson
 * @since 0.1
 */
public interface PromotionOrderBillService {

  /**
   * 通过订单标识取得订单。
   * 
   * @param orderId
   *          订单标识，禁止为null。
   * @return 订单， 如果没有找到返回null。
   */
  public OrderBill getOrderById(String orderId);

}
