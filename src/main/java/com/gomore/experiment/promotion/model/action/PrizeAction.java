/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	GoodsAction.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月13日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.action;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.gomore.experiment.promotion.common.UCN;
import com.google.common.collect.Lists;

/**
 * 赠送指定数量的奖品。
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public class PrizeAction extends AbstractAction {
  private static final long serialVersionUID = 8931308802439464841L;

  public static final String CTYPE = "prizeAction";
  public static final int DEFAULT_COUNT = 1;

  private UCN prize;
  private Integer count = DEFAULT_COUNT;

  public PrizeAction() {
  }

  public PrizeAction(UCN prize) {
    this(prize, DEFAULT_COUNT);
  }

  public PrizeAction(UCN prize, int count) {
    this.prize = prize;
    this.count = count;
  }

  /** 奖品 */
  public UCN getPrize() {
    return prize;
  }

  public void setPrize(UCN prize) {
    this.prize = prize;
  }

  /** 数量 */
  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  @Override
  public List<Action> stepActions(BigDecimal step) {
    setCount(getCount().intValue() * step.intValue());
    return Lists.newArrayList((Action) this);
  }

  @Override
  public String getType() {
    return CTYPE;
  }

}
