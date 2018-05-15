/**
 * 版权所有(C)，上海勾芒信息科技，2017，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	CouponAction.java
 * 模块说明：	
 * 修改历史：
 * 2017年8月25日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.action;

import com.gomore.experiment.promotion.common.UCN;

/**
 * 送券的促销结果
 * 
 * @author Debenson
 * @since 0.1
 */
public class CouponAction extends AbstractAction {
  private static final long serialVersionUID = 8288124222909330251L;

  public static final String CTYPE = "couponAction";

  private UCN activity;
  private Integer count = 1;

  public CouponAction() {
  }

  public CouponAction(UCN activity) {
    this.activity = activity;
  }

  public CouponAction(UCN activity, int count) {
    this.activity = activity;
    this.count = count;
  }

  /** 券活动 */
  public UCN getActivity() {
    return activity;
  }

  public void setActivity(UCN activity) {
    this.activity = activity;
  }

  /** 赠送数量 */
  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  @Override
  public String getType() {
    return CTYPE;
  }

}
