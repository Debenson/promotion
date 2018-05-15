/**
 * 版权所有(C)，上海勾芒信息科技，2017，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	Step.java
 * 模块说明：	
 * 修改历史：
 * 2017年3月14日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.condition.step;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用来描述阶梯叠加。 <br>
 * 阶梯叠加包含三个属性， 起始值、结束值和步长。
 * 
 * @author Debenson
 * @since 0.1
 */
public class Stair implements Serializable {
  private static final long serialVersionUID = -2579815369896543412L;

  private BigDecimal from;
  private BigDecimal to;
  private BigDecimal value;

  /**
   * 起始条件。不可空。
   * 
   * @return
   */
  public BigDecimal getFrom() {
    return this.from;
  }

  public void setFrom(BigDecimal from) {
    this.from = from;
  }

  /**
   * 截止条件，可空。空表示无限大。
   * 
   * @return
   */
  public BigDecimal getTo() {
    return this.to;
  }

  public void setTo(BigDecimal to) {
    this.to = to;
  }

  /**
   * 步长，不可空。
   * 
   * @return
   */
  public BigDecimal getValue() {
    return this.value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

}
