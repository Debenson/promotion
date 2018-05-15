/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	CaseRuleImpl.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月22日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.exp;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Lists;

/**
 * 多路径分支表达式。
 * 
 * <br>
 * 执行时只能其中一条表达式，多个条件不能冲突。
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public class CaseExpression extends AbstractExpression {
  private static final long serialVersionUID = -3095927566261316764L;

  public static final String CTYPE = "caseExpression";

  private List<IfThenExpression> cases = Lists.newArrayList();

  @Override
  public String getType() {
    return CTYPE;
  }

  /**
   * 取得分支促销表达式。
   * 
   * @return
   */
  public List<IfThenExpression> getCases() {
    return cases;
  }

  public void setCases(List<IfThenExpression> cases) {
    this.cases = cases;
  }

}
