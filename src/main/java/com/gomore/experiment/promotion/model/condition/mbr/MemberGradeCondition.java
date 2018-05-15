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

import com.gomore.experiment.promotion.common.UCN;
import com.gomore.experiment.promotion.model.condition.AbstractCondition;

/**
 * 会员等级促销条件
 * 
 * @author Debenson
 * @since 0.1
 */
public class MemberGradeCondition extends AbstractCondition {
  private static final long serialVersionUID = -5408454970123974886L;

  public static final String CTYPE = "memberGradeCondition";

  private UCN grade;

  @Override
  public String getType() {
    return CTYPE;
  }

  /** 会员等级大于等于 */
  public UCN getGrade() {
    return grade;
  }

  public void setGrade(UCN grade) {
    this.grade = grade;
  }

}
