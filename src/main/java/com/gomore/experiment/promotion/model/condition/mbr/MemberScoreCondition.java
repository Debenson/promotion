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

import java.math.BigDecimal;

import com.gomore.experiment.promotion.model.condition.AmountCondition;

/**
 * 会员积分促销条件
 * 
 * @author Debenson
 * @since 0.1
 */
public class MemberScoreCondition extends AmountCondition {
  private static final long serialVersionUID = -5408454970123974886L;

  public static final String CTYPE = "memberScoreCondition";

  @Override
  public String getType() {
    return CTYPE;
  }

  /** 会员积分 */
  @Override
  public BigDecimal getTotal() {
    return super.getTotal();
  }

}
