/**
 * 版权所有(C)，上海勾芒信息科技，2018，所有权利保留。
 * 
 * 项目名：	newretail-promotion
 * 文件名：	PromotionBillQueryFilter.java
 * 模块说明：	
 * 修改历史：
 * 2018年3月14日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.bill.bean;

import com.gomore.experiment.commons.rest.FilterWithOrder;
import com.gomore.experiment.commons.rest.PageFilter;
import com.gomore.experiment.commons.rest.QueryOrderDirection;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 促销单查询条件
 * 
 * @author Debenson
 * @since 0.1
 */
@Data
@ApiModel(description = "促销单查询条件")
@EqualsAndHashCode(callSuper = false)
public class PromotionBillFilter extends PageFilter<PromotionBill> implements FilterWithOrder {
  private static final long serialVersionUID = -8245146824562549564L;

  /** 状态 */
  public static final String ORDER_FIELD_STATE = "state";
  /** 促销主题 */
  public static final String ORDER_FIELD_SUBJECT = "subject";
  /** 最后修改时间 */
  public static final String ORDER_FIELD_UPDATE_TIME = "updateTime";

  /** 组织标识等于 */
  @ApiModelProperty(value = "组织标识等于")
  private Long orgIdEquals;

  /** 单据标识等于 */
  @ApiModelProperty(value = "单据标识等于")
  private Long idEquals;

  /** 包含状态 */
  @ApiModelProperty(value = "包含状态")
  private PromotionBillState[] stateIn;

  /** 促销主题类似于 */
  @ApiModelProperty(value = "促销主题类似于")
  private String subjectLike;

  /** 仅当前生效的 */
  @ApiModelProperty(value = "仅当前生效的")
  private Boolean effectived;

  /** 仅已过期，仅指生效时间已经小于当前时间。 */
  @ApiModelProperty(value = "仅已过期，仅指生效时间已经小于当前时间")
  private Boolean expiredOnly;

  /** 排序字段，支持state（状态）， subject（促销主题）, updateTime（最后修改时间） */
  @ApiModelProperty(value = "排序字段", allowableValues = ORDER_FIELD_STATE + "," + ORDER_FIELD_SUBJECT
      + "," + ORDER_FIELD_UPDATE_TIME)
  private String orderField;

  /** 排序方向 */
  @ApiModelProperty(value = "排序方向")
  private QueryOrderDirection orderDirection = QueryOrderDirection.asc;

}
