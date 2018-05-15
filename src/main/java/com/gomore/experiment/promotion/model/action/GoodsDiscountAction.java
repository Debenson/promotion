/**
 * 版权所有(C)，上海勾芒信息科技，2018，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	GoodsDiscountAction.java
 * 模块说明：	
 * 修改历史：
 * 2018年5月2日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.action;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gomore.experiment.promotion.model.condition.GoodsRange;

/**
 * 商品折扣促销结果 <br>
 * 只对生效的商品进行折扣
 * 
 * @author Debenson
 * @since 0.1
 */
public class GoodsDiscountAction extends AbstractAction implements GoodsRangeAction {
  private static final long serialVersionUID = -3872679941812352281L;

  public static final String CTYPE = "goodsDiscountAction";

  /**
   * 第N件商品的折扣率, 如: 80, 50，表示第一件商品0.8折，第二件0.5折。 另外，支持叠加。
   */
  private List<Integer> rates;

  /**
   * 生效商品范围 , 该值为运行时属性,不序列化
   */
  @JsonIgnore
  private GoodsRange goodsRange;

  @Override
  public String getType() {
    return CTYPE;
  }

  @Override
  public GoodsRange getGoodsRange() {
    return goodsRange;
  }

  @Override
  public void setGoodsRange(GoodsRange range) {
    this.goodsRange = range;
  }

  /**
   * 商品折扣率
   * 
   * @return
   */
  public List<Integer> getRates() {
    return rates;
  }

  public void setRates(List<Integer> rates) {
    this.rates = rates;
  }

}
