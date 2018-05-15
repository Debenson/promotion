/**
 * 版权所有(C)，上海勾芒信息科技，2018，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	GoodsRangeCondition.java
 * 模块说明：	
 * 修改历史：
 * 2018年5月2日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.condition;

/**
 * 具有商品生效范围属性的促销条件。
 * 
 * @author Debenson
 * @since 0.1
 */
public interface GoodsRangeCondition extends Condition {

  /**
   * 取得生效的商品范围
   * 
   * @return
   */
  GoodsRange getGoodsRange();

}
