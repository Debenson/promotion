/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	PromotionBillState.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月22日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.bill.bean;

/**
 * 促销单状态。
 * 
 * @author Debenson
 * @since 0.1
 */
public enum PromotionBillState {

  /** 未提交 */
  unsubmit,

  /** 已提交 */
  submit,

  /** 已作废 */
  abolished,

  /** 已过期 */
  expired,

}
