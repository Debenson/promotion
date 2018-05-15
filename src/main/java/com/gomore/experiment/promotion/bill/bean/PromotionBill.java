/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	PromotionBill.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月22日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.bill.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gomore.experiment.commons.dao.po.StandardEntity;
import com.gomore.experiment.promotion.common.UCN;
import com.gomore.experiment.promotion.model.condition.BusinessCondition;
import com.gomore.experiment.promotion.model.condition.CompositeCondition;
import com.gomore.experiment.promotion.model.condition.Condition;
import com.gomore.experiment.promotion.model.condition.StoreCondition;
import com.gomore.experiment.promotion.model.condition.TimeRangeCondition;
import com.gomore.experiment.promotion.model.exp.Expression;
import com.google.common.collect.Lists;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 促销单。
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "促销单")
public class PromotionBill extends StandardEntity {
  private static final long serialVersionUID = 6341834187373167339L;

  /**
   * 状态
   */
  @ApiModelProperty(value = "促销单状态，可选值: unsubmit: 未提交， submit：已提交, abolished: 已作废, expired: 已过期")
  private PromotionBillState state;
  /**
   * 促销主题
   */
  @NotEmpty(message = "促销主题不能为空")
  @Length(min = 1, max = 50, message = "促销主题长度必须介于[1,50]")
  @ApiModelProperty(value = "促销主题", required = true, allowableValues = "range[1, 50]")
  private String subject;
  /**
   * 备注
   */
  @ApiModelProperty(value = "备注", required = false, allowableValues = "range[0, 255]")
  private String remark;
  /**
   * 前置条件，就是必须先满足的条件，用户自定义。促销计算时会先计算该条件是否满足，只满足了前置条件才会再计算促销表达式. 一般会有：
   * 促销时间条件，生效门店条件等
   */
  @ApiModelProperty(value = "前置条件", required = true)
  private Condition precondition;
  /**
   * 促销表达式
   */
  @ApiModelProperty(value = "促销表达式", required = false)
  private List<Expression> exps = Lists.newArrayList();

  public void addExp(Expression... expressions) {
    if (expressions != null) {
      for (Expression exp : expressions) {
        this.exps.add(exp);
      }
    }
  }

  /**
   * 取得生效门店
   * 
   * @return
   */
  @JsonIgnore
  public List<UCN> getStores() {
    try {
      CompositeCondition<?> cc = (CompositeCondition<?>) this.precondition;
      StoreCondition sc = (StoreCondition) ((CompositeCondition<?>) cc.getConditions().get(1))
          .getConditions().get(0);
      return sc.getStores();
    } catch (Exception e) {
      return Lists.newArrayList();
    }
  }

  /**
   * 取得生效业态
   * 
   * @return
   */
  @JsonIgnore
  public List<UCN> getBusiness() {
    try {
      CompositeCondition<?> cc = (CompositeCondition<?>) this.precondition;
      BusinessCondition bc = (BusinessCondition) ((CompositeCondition<?>) cc.getConditions().get(0))
          .getConditions().get(1);
      return bc.getBusiness();
    } catch (Exception e) {
      return Lists.newArrayList();
    }
  }

  /**
   * 取得生效时间范围
   * 
   * @return
   */
  @JsonIgnore
  public TimeRangeCondition getTimeRange() {
    try {
      CompositeCondition<?> cc = (CompositeCondition<?>) this.precondition;
      TimeRangeCondition trc = (TimeRangeCondition) cc.getConditions().get(0);
      return trc;
    } catch (Exception e) {
      return null;
    }
  }

}
