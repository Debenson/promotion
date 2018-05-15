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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品查询条件
 * 
 * @author Debenson
 * @since 0.1
 */
@Data
@ApiModel(description = "商品查询条件")
@EqualsAndHashCode(callSuper = false)
public class GoodsFilter extends PageFilter<Goods> {
  private static final long serialVersionUID = 1637792197455111413L;

  /**
   * 生效门店ID列表，传空表示全部门店
   */
  @ApiModelProperty("生效门店ID列表，传空表示全部门店，多个值以英文逗号分隔")
  private String storeIds;

  /**
   * 代码等于
   */
  @ApiModelProperty("代码等于")
  private String codeEqulas;

  /**
   * 名称类似于
   */
  @ApiModelProperty("名称类似于")
  private String nameLikes;

}
