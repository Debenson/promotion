/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	DiscountAction.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月22日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.action;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Lists;

/**
 * 订单折扣。
 * 
 * <br>
 * {@link DiscountAction#getTotal()}表示折扣率，取值范围[0,100]。
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public class DiscountAction extends AmountAction {
  private static final long serialVersionUID = -6493947515482316367L;

  public static final String CTYPE = "discountAction";

  public DiscountAction() {
  }

  public DiscountAction(BigDecimal total) {
    super(total);
  }

  @Override
  public String getType() {
    return CTYPE;
  }

  @Override
  public List<Action> stepActions(BigDecimal step) {
    // 折扣只保留二位小数
    BigDecimal oldDiscount = getTotal().divide(BigDecimal.valueOf(10));
    BigDecimal newDiscount = BigDecimal
        .valueOf(Math.pow(oldDiscount.doubleValue(), step.doubleValue()))
        .setScale(2, BigDecimal.ROUND_HALF_DOWN);
    setTotal(newDiscount);
    return Lists.newArrayList((Action) this);
  }

}
