/**
 * 版权所有(C)，上海勾芒信息科技，2017，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	CouponService.java
 * 模块说明：	
 * 修改历史：
 * 2017年8月28日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.service;

import com.gomore.experiment.commons.rest.PageResult;
import com.gomore.experiment.promotion.common.UCN;
import com.gomore.experiment.promotion.model.action.CouponAction;
import com.gomore.experiment.promotion.service.bean.OrderBill;
import com.gomore.experiment.promotion.service.bean.UCNPageFilter;

/**
 * 券活动相关
 * 
 * @author Debenson
 * @since 0.1
 */
public interface PromotionCouponService {

  /**
   * 分页查询券活动
   * 
   * @param filter
   *          券活动查询条件
   * @return
   */
  PageResult<UCN> queryCoupon(UCNPageFilter filter);

  /**
   * 制卡并领用
   * 
   * @param bill
   *          业务单据
   * @param action
   *          促销事件
   */
  void makeAndAssignCoupon(OrderBill bill, CouponAction action);

}
