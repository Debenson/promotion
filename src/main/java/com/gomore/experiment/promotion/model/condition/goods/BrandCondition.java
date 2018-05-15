/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	BrandCondition.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月22日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.condition.goods;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gomore.experiment.promotion.common.UCN;
import com.gomore.experiment.promotion.model.condition.AbstractCondition;
import com.gomore.experiment.promotion.model.condition.GoodsRange;
import com.gomore.experiment.promotion.model.condition.GoodsRangeCondition;

/**
 * 商品品牌条件。
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public class BrandCondition extends AbstractCondition implements GoodsRangeCondition {
  private static final long serialVersionUID = -7614714012024235635L;

  public static final String CTYPE = "brandCondition";

  /** 表示全部品牌 */
  public static final UCN ANY_BRAND = new UCN("ALL", "ALL", "全部");

  private UCN brand;

  public BrandCondition() {
  }

  /**
   * 指定品牌
   * 
   * @param brand
   */
  public BrandCondition(UCN brand) {
    this.brand = brand;
  }

  @Override
  public String getType() {
    return CTYPE;
  }

  /** 商品品牌 */
  @NotNull
  public UCN getBrand() {
    return brand;
  }

  public void setBrand(UCN brand) {
    this.brand = brand;
  }

  @JsonIgnore
  public boolean acceptAny() {
    return ANY_BRAND.equals(getBrand());
  }

  @Override
  @JsonIgnore
  public GoodsRange getGoodsRange() {
    if (acceptAny()) {
      return GoodsRange.allGoods();
    } else if (brand != null && StringUtils.isNotBlank(brand.getUuid())) {
      return GoodsRange.brand(brand);
    } else {
      return null;
    }
  }

}
