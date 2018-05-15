/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	AmountCondition.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月13日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.condition;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import com.gomore.experiment.promotion.model.condition.step.AbstractStepCondition;

/**
 * 数量促销条件。
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public class CountCondition extends AbstractStepCondition {
  private static final long serialVersionUID = 3875418516567618761L;

  public static final String CTYPE = "countCondition";

  private BigDecimal count = BigDecimal.ZERO;
  private Comparisons comparison = Comparisons.gte;

  public CountCondition() {
  }

  public CountCondition(BigDecimal total) {
    this(total, Comparisons.gte);
  }

  public CountCondition(BigDecimal total, Comparisons comparison) {
    this.count = total;
    this.comparison = comparison;
  }

  @Override
  public String getType() {
    return CTYPE;
  }

  /** 金额 */
  public BigDecimal getCount() {
    return count;
  }

  public void setCount(BigDecimal count) {
    this.count = count;
  }

  /** 比较运算符 */
  public Comparisons getComparison() {
    return comparison;
  }

  public void setComparison(Comparisons comparison) {
    this.comparison = comparison;
  }

}
