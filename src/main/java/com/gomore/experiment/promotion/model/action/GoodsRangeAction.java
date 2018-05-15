/**
 * 版权所有(C)，上海勾芒信息科技，2018，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	GoodsRangeAction.java
 * 模块说明：	
 * 修改历史：
 * 2018年5月2日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.action;

import com.gomore.experiment.promotion.model.condition.GoodsRange;

/**
 * 具有生效商品范围的促销结果
 * 
 * @author Debenson
 * @since 0.1
 */
public interface GoodsRangeAction extends Action {

  /**
   * 取得生效的商品范围
   * 
   * @return
   */
  GoodsRange getGoodsRange();

  /**
   * 设置生效的商品范围
   * 
   * @param range
   */
  void setGoodsRange(GoodsRange range);

}
