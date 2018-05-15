/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	Rule.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月14日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.exp;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.gomore.experiment.promotion.model.SerializationConstants;

/**
 * 促销表达式。
 * 
 * <br>
 * 一个促销表达式应该由条件+促销结果组成，业务上表示：如果满足条件，则执行促销动作。
 * 
 * @author Debenson
 * @since 0.1
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY,
    property = SerializationConstants.JSON_TYPE_FIELD)
@JsonSubTypes({
    @Type(value = IfThenExpression.class, name = IfThenExpression.CTYPE),
    @Type(value = CaseExpression.class, name = CaseExpression.CTYPE) })
public interface Expression extends Serializable {

  /**
   * 表达式类型
   * 
   * @return
   */
  @JsonIgnore
  public String getType();

}
