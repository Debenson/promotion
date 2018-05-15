/**
 * 版权所有(C)，上海勾芒信息科技，2017，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	MemberGradeCondition.java
 * 模块说明：	
 * 修改历史：
 * 2017年8月28日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.condition.mbr;

import com.gomore.experiment.promotion.model.condition.AbstractCondition;

/**
 * 会员生日促销条件
 * 
 * @author Debenson
 * @since 0.1
 */
public class MemberBirthdayCondition extends AbstractCondition {
  private static final long serialVersionUID = 4569187513765745260L;

  public static final String CTYPE = "memberBirthdayCondition";

  private Boolean isBirthday;

  @Override
  public String getType() {
    return CTYPE;
  }

  /** 是否是会员生日 */
  public Boolean getIsBirthday() {
    return isBirthday;
  }

  public void setIsBirthday(Boolean isBirthday) {
    this.isBirthday = isBirthday;
  }

}
