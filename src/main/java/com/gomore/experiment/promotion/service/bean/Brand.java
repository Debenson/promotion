/**
 * 版权所有(C)，上海勾芒信息科技，2018，所有权利保留。
 * 
 * 项目名：	newretail-promotion
 * 文件名：	Category.java
 * 模块说明：	
 * 修改历史：
 * 2018年3月15日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.service.bean;

import com.gomore.experiment.promotion.common.UCN;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品品牌
 * 
 * @author Debenson
 * @since 0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "商品品牌")
public class Brand extends UCN {
  private static final long serialVersionUID = 8832006489447906174L;

  /**
   * 品牌图片
   */
  private String image;

}
