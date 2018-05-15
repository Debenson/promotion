/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	PromotionConditions.java
 * 模块说明：	
 * 修改历史：
 * 2016年8月9日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.condition;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.gomore.experiment.promotion.model.SerializationConstants;
import com.gomore.experiment.promotion.model.condition.basket.BasketCondition;
import com.gomore.experiment.promotion.model.condition.event.EventCondition;
import com.gomore.experiment.promotion.model.condition.goods.BrandCondition;
import com.gomore.experiment.promotion.model.condition.goods.CategoryCondition;
import com.gomore.experiment.promotion.model.condition.goods.GoodsCondition;
import com.gomore.experiment.promotion.model.condition.mbr.MemberBirthdayCondition;
import com.gomore.experiment.promotion.model.condition.mbr.MemberDayCondition;
import com.gomore.experiment.promotion.model.condition.mbr.MemberGradeCondition;
import com.gomore.experiment.promotion.model.condition.mbr.MemberScoreCondition;

/**
 * 促销条件。
 * 
 * @author Debenson
 * @since 0.1
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY,
    property = SerializationConstants.JSON_TYPE_FIELD)
@JsonSubTypes({
    @Type(value = CompositeCondition.class, name = CompositeCondition.CTYPE),
    @Type(value = ScriptCondition.class, name = ScriptCondition.CTYPE),
    @Type(value = StoreCondition.class, name = StoreCondition.CTYPE),
    @Type(value = BusinessCondition.class, name = BusinessCondition.CTYPE),
    @Type(value = TimeRangeCondition.class, name = TimeRangeCondition.CTYPE),
    @Type(value = BrandCondition.class, name = BrandCondition.CTYPE),
    @Type(value = CategoryCondition.class, name = CategoryCondition.CTYPE),
    @Type(value = GoodsCondition.class, name = GoodsCondition.CTYPE),
    @Type(value = CountCondition.class, name = CountCondition.CTYPE),
    @Type(value = AmountCondition.class, name = AmountCondition.CTYPE),
    @Type(value = BasketCondition.class, name = BasketCondition.CTYPE),
    @Type(value = MemberGradeCondition.class, name = MemberGradeCondition.CTYPE),
    @Type(value = MemberScoreCondition.class, name = MemberScoreCondition.CTYPE),
    @Type(value = MemberBirthdayCondition.class, name = MemberBirthdayCondition.CTYPE),
    @Type(value = MemberDayCondition.class, name = MemberDayCondition.CTYPE),
    @Type(value = EventCondition.class, name = EventCondition.CTYPE), })
public interface Condition extends Serializable {

  /**
   * 条件类型
   * 
   * @return
   */
  @JsonIgnore
  public String getType();

}
