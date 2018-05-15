/**
 * 版权所有(C)，上海勾芒信息科技，2018，所有权利保留。
 * 
 * 项目名：	newretail-promotion
 * 文件名：	GoodsService.java
 * 模块说明：	
 * 修改历史：
 * 2018年3月14日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.service;

import com.gomore.experiment.commons.rest.PageResult;
import com.gomore.experiment.promotion.service.bean.Goods;
import com.gomore.experiment.promotion.service.bean.GoodsFilter;

/**
 * 促销商品服务接口
 * 
 * @author Debenson
 * @since 0.1
 */
public interface PromotionGoodsService {

  /**
   * 分页查询商品
   * 
   * @param filter
   *          商品查询条件
   * @return
   */
  PageResult<Goods> queryGoods(GoodsFilter filter);

}
