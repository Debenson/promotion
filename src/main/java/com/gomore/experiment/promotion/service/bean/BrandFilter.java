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

import org.hibernate.validator.constraints.Length;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gomore.experiment.commons.rest.PageFilter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品品牌查询条件
 * 
 * @author Debenson
 * @since 0.1
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "商品品牌查询条件")
public class BrandFilter extends PageFilter<Brand> {
  private static final long serialVersionUID = 1637792197455111413L;

  /**
   * 名称类似于
   */
  @Length(min = 1, max = 100)
  @ApiModelProperty(value = "名称类似于", allowableValues = "range[1, 100]")
  private String nameLikes;

  @Override
  public Wrapper<Brand> buildConditions() {
    // TODO Auto-generated method stub
    return null;
  }

}
