/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	AbstractAction.java
 * 模块说明：	
 * 修改历史：
 * 2016年10月13日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.action;

import java.math.BigDecimal;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * 促销结果基类，简化子类继承。
 * 
 * @author Debenson
 * @since 0.1
 */
public abstract class AbstractAction implements Action {
  private static final long serialVersionUID = 5961902254038583736L;

  private ActionBody body = ActionBody.consumer;
  private String description;
  private Integer priority = DEF_PRIORITY;
  private String promotionBillNumber;
  private List<String> conflicts = Lists.newArrayList();

  @Override
  public ActionBody getBody() {
    return body;
  }

  public void setBody(ActionBody body) {
    this.body = body;
  }

  @Override
  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public List<String> getConflicts() {
    return conflicts;
  }

  public void setConflicts(List<String> conflicts) {
    this.conflicts = conflicts;
  }

  @Override
  public String getPromotionBillNumber() {
    return promotionBillNumber;
  }

  @Override
  public void setPromotionBillNumber(String promotionBillNumber) {
    this.promotionBillNumber = promotionBillNumber;
  }

  /** 默认不支持叠加 */
  @Override
  public List<Action> stepActions(BigDecimal step) {
    return Lists.newArrayList((Action) this);
  }

}
