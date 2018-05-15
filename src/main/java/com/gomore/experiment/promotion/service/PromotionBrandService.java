/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	CategoryService.java
 * 模块说明：	
 * 修改历史：
 * 2016年11月7日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.service;

import com.gomore.experiment.commons.rest.PageResult;
import com.gomore.experiment.promotion.service.bean.Brand;
import com.gomore.experiment.promotion.service.bean.BrandFilter;

/**
 * 商品品牌服务。
 * 
 * @author Debenson
 * @since 0.1
 */
public interface PromotionBrandService {

  /**
   * 分页查询商品品牌
   * 
   * @param filter
   *          商品品牌查询条件
   * @return
   */
  PageResult<Brand> queryBrand(BrandFilter filter);

}
