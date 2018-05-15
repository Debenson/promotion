/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	AmountAction.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月13日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.action;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Lists;

/**
 * 金额动作。
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public abstract class AmountAction extends AbstractAction {
  private static final long serialVersionUID = -5907457414776243318L;

  private BigDecimal total = BigDecimal.ZERO;

  public AmountAction() {
  }

  public AmountAction(BigDecimal total) {
    this.total = total;
  }

  /** 金额 */
  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  @Override
  public List<Action> stepActions(BigDecimal step) {
    setTotal(getTotal().multiply(step).setScale(2, BigDecimal.ROUND_HALF_DOWN));
    return Lists.newArrayList((Action) this);
  }

}
