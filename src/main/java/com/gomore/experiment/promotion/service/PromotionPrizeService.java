/**
 * 版权所有(C)，上海勾芒信息科技，2018，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	PrizeService.java
 * 模块说明：	
 * 修改历史：
 * 2018年3月15日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.service;

import com.gomore.experiment.promotion.model.action.PrizeAction;
import com.gomore.experiment.promotion.service.bean.OrderBill;

/**
 * 奖品服务
 * 
 * @author Debenson
 * @since 0.1
 */
public interface PromotionPrizeService {

  /**
   * 赠送奖品
   * 
   * @param bill
   *          业务单据
   * @param action
   *          促销事件
   */
  public void givePrize(OrderBill bill, PrizeAction action);

}
