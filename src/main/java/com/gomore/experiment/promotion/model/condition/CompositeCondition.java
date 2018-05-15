/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	CompositeCondition.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月14日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.condition;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Lists;

/**
 * 组合条件。
 * 
 * <br>
 * 用来组合一组促销条件， 不要求各个促销条件类型一致。
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public class CompositeCondition<T extends Condition> extends AbstractCondition {
  private static final long serialVersionUID = -9203859192218687237L;

  public static final String CTYPE = "compositeCondition";

  private List<T> conditions = Lists.newArrayList();
  private Logical logical = Logical.and;

  public CompositeCondition() {
  }

  @SuppressWarnings("unchecked")
  public CompositeCondition(Logical logical, T... conditions) {
    this.logical = logical;
    if (conditions != null) {
      this.conditions.addAll(Arrays.asList(conditions));
    }
  }

  @Override
  public String getType() {
    return CTYPE;
  }

  @SuppressWarnings({
      "rawtypes", "unchecked" })
  public static CompositeCondition and(Condition... conditions) {
    return new CompositeCondition(Logical.and, conditions);
  }

  @SuppressWarnings({
      "rawtypes", "unchecked" })
  public static CompositeCondition or(Condition... conditions) {
    return new CompositeCondition(Logical.or, conditions);
  }

  /** 促销条件 */
  public List<T> getConditions() {
    return conditions;
  }

  public void setConditions(List<T> conditions) {
    this.conditions = conditions;
  }

  /** 逻辑运算符 */
  public Logical getLogical() {
    return logical;
  }

  public void setLogical(Logical logical) {
    this.logical = logical;
  }

  /**
   * 添加促销条件。
   * 
   * @param conditions
   */
  @SuppressWarnings("unchecked")
  public void addCondition(Condition... conditions) {
    if (conditions != null) {
      for (Condition cond : conditions) {
        this.conditions.add((T) cond);
      }
    }
  }
}
