/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	GoodsCondition.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月13日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.condition.goods;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gomore.experiment.promotion.common.UCN;
import com.gomore.experiment.promotion.model.condition.AbstractCondition;
import com.gomore.experiment.promotion.model.condition.GoodsRange;
import com.gomore.experiment.promotion.model.condition.GoodsRangeCondition;

/**
 * 商品条件。 <br>
 * 指交易的商品在。。。之中。一般而言， 如果没有设置，就默认为全部商品。
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public class GoodsCondition extends AbstractCondition implements GoodsRangeCondition {
  private static final long serialVersionUID = -6629785244618037510L;

  public static final String CTYPE = "goodsCondition";

  /** 表示全部商品 */
  public static final UCN ANY_GOODS = new UCN("ALL", "ALL", "全部");

  private UCN goods;

  public GoodsCondition() {
  }

  /**
   * 指定商品
   * 
   * @param goods
   */
  public GoodsCondition(UCN goods) {
    Assert.notNull(goods, "goods");
    this.goods = goods;
  }

  @Override
  public String getType() {
    return CTYPE;
  }

  /** 商品 */
  @NotNull
  public UCN getGoods() {
    return goods;
  }

  public void setGoods(UCN goods) {
    this.goods = goods;
  }

  @JsonIgnore
  public boolean acceptAny() {
    return ANY_GOODS.equals(getGoods());
  }

  @Override
  @JsonIgnore
  public GoodsRange getGoodsRange() {
    if (acceptAny()) {
      return GoodsRange.allGoods();
    } else if (goods != null && StringUtils.isNotBlank(goods.getUuid())) {
      return GoodsRange.goods(goods);
    } else {
      return null;
    }
  }

}
