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
import com.gomore.experiment.promotion.common.HasUCN;
import com.gomore.experiment.promotion.common.UCN;
import com.gomore.experiment.promotion.service.bean.CategoryFilter;

/**
 * 商品分类服务。
 * 
 * @author Debenson
 * @since 0.1
 */
public interface PromotionCategoryService {

  /**
   * 判断lower是否为upper的下级分类。
   * 
   * @param lower
   *          下级分类。
   * @param upper
   *          上级分类。
   * @return 特别地，如果lower.equlas(upper)也返回true。
   */
  public boolean categoryBelongTo(HasUCN lower, HasUCN upper);

  /**
   * 分页查询商品分类
   * 
   * @param filter
   *          商品分类查询条件
   * @return
   */
  PageResult<UCN> queryCategory(CategoryFilter filter);

}
