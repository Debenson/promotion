/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	IfThenRuleImpl.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月22日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.exp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.gomore.experiment.promotion.model.action.Action;
import com.gomore.experiment.promotion.model.condition.Condition;

/**
 * IF-THEN促销表达式。
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public class IfThenExpression extends AbstractExpression {
  private static final long serialVersionUID = -4906974415321365447L;

  public static final String CTYPE = "ifThenExpression";

  private Condition condition;
  private List<Action> actions = new ArrayList<>();

  public IfThenExpression() {
  }

  public IfThenExpression(Condition condition, List<Action> actions) {
    this.condition = condition;
    this.actions = actions;
  }

  public IfThenExpression(Condition condition, Action... actions) {
    this.condition = condition;
    if (actions != null) {
      this.actions = Arrays.asList(actions);
    }
  }

  @Override
  public String getType() {
    return CTYPE;
  }

  /**
   * 取得IF促销条件。
   * 
   * @return
   */
  public Condition getCondition() {
    return condition;
  }

  public void setCondition(Condition condition) {
    this.condition = condition;
  }

  /**
   * 取得Then促销动作
   * 
   * @return
   */
  public List<Action> getActions() {
    return actions;
  }

  public void setActions(List<Action> actions) {
    this.actions = actions;
  }

}
