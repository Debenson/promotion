/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	DeductionAction.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月13日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.action;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 抵扣订单金额。
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public class DeductionAction extends AmountAction {
  private static final long serialVersionUID = -7106194355946717159L;

  public static final String CTYPE = "deductionAction";

  public DeductionAction() {
  }

  public DeductionAction(BigDecimal total) {
    super(total);
  }

  @Override
  public String getType() {
    return CTYPE;
  }

}
