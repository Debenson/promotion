/**
 * 版权所有(C)，上海勾芒信息科技，2018，所有权利保留。
 * 
 * 项目名：	newretail-promotion
 * 文件名：	GoodsFilter.java
 * 模块说明：	
 * 修改历史：
 * 2018年3月14日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.service.bean;

import com.gomore.experiment.commons.rest.PageFilter;
import com.gomore.experiment.promotion.common.UCN;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支付UUIC+CODE+NAME的分页查询条件
 * 
 * @author Debenson
 * @since 0.1
 */
@Data
@ApiModel(description = "支付UUIC+CODE+NAME的分页查询条件")
@EqualsAndHashCode(callSuper = false)
public class UCNPageFilter extends PageFilter<UCN> {
  private static final long serialVersionUID = 1637792197455111413L;

  /**
   * UUID等于
   */
  @ApiModelProperty(value = "UUID等于")
  private String uuidEquals;

  /**
   * 编码等于
   */
  @ApiModelProperty(value = "编码等于")
  private String codeEquals;

  /**
   * 名称类似于
   */
  @ApiModelProperty(value = "名称类似于")
  private String nameLikes;

}
