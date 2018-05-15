/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	NScoreAction.java
 * 模块说明：	
 * 修改历史：
 * 2016年10月3日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.action;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 翻倍积分
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public class NScoreAction extends AmountAction {
  private static final long serialVersionUID = -1040163585547911458L;

  public static final String CTYPE = "nscoreAction";

  public NScoreAction() {
    this(BigDecimal.ONE);
  }

  /**
   * @param times
   *          翻倍系数。
   */
  public NScoreAction(BigDecimal times) {
    super(times);
  }

  @Override
  public String getType() {
    return CTYPE;
  }

}
