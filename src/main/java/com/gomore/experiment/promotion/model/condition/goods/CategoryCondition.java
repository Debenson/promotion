/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	GoodsCategoryCondition.java
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
 * 商品分类促销条件。
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public class CategoryCondition extends AbstractCondition implements GoodsRangeCondition {
  private static final long serialVersionUID = 7284899423683792421L;

  public static final String CTYPE = "categoryCondition";

  /** 表示全部分类 */
  public static final UCN ANY_CATEGORY = new UCN("ALL", "ALL", "全部");

  private UCN category;

  public CategoryCondition() {
  }

  /**
   * 指定分类
   * 
   * @param category
   */
  public CategoryCondition(UCN category) {
    this.category = category;
  }

  @Override
  public String getType() {
    return CTYPE;
  }

  /** 商品分类 */
  @NotNull
  public UCN getCategory() {
    return category;
  }

  public void setCategory(UCN category) {
    this.category = category;
  }

  @JsonIgnore
  public boolean acceptAny() {
    return ANY_CATEGORY.equals(getCategory());
  }

  @Override
  @JsonIgnore
  public GoodsRange getGoodsRange() {
    if (acceptAny()) {
      return GoodsRange.allGoods();
    } else if (category != null && StringUtils.isNotBlank(category.getUuid())) {
      return GoodsRange.category(category);
    } else {
      return null;
    }
  }

}
