/**
 * 版权所有(C)，上海勾芒信息科技，2018，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	EffectiveGoodsRange.java
 * 模块说明：	
 * 修改历史：
 * 2018年5月2日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.condition;

import java.io.Serializable;
import java.util.List;

import com.gomore.experiment.promotion.common.UCN;
import com.google.common.collect.Lists;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 生效的商品范围
 * 
 * @author Debenson
 * @since 0.1
 */
@Data
@Accessors(chain = true)
public class GoodsRange implements Serializable {
  private static final long serialVersionUID = 1072569157033960792L;

  /**
   * 所有商品都生效
   */
  private boolean allGoods;
  /**
   * 生效的商品条件
   */
  private List<UCN> goods = Lists.newArrayList();
  /**
   * 生效的商品分类条件
   */
  private List<UCN> categories = Lists.newArrayList();
  /**
   * 生效的商品品牌条件
   */
  private List<UCN> brands = Lists.newArrayList();

  /**
   * 所有商品
   * 
   * @return
   */
  public static GoodsRange allGoods() {
    GoodsRange range = new GoodsRange();
    range.setAllGoods(true);
    return range;
  }

  /**
   * 指定商品
   * 
   * @param goods
   * @return
   */
  public static GoodsRange goods(UCN... goods) {
    GoodsRange range = new GoodsRange();
    if (goods != null) {
      for (UCN g : goods) {
        range.getGoods().add(g);
      }
    }
    return range;
  }

  /**
   * 指定分类
   * 
   * @param categories
   * @return
   */
  public static GoodsRange category(UCN... categories) {
    GoodsRange range = new GoodsRange();
    if (categories != null) {
      for (UCN cg : categories) {
        range.getCategories().add(cg);
      }
    }
    return range;
  }

  /**
   * 指定品牌
   * 
   * @param brands
   * @return
   */
  public static GoodsRange brand(UCN... brands) {
    GoodsRange range = new GoodsRange();
    if (brands != null) {
      for (UCN cg : brands) {
        range.getBrands().add(cg);
      }
    }
    return range;
  }

  /**
   * 合并商品范围
   * 
   * @param a
   * @param b
   * @return
   */
  public static GoodsRange merge(GoodsRange a, GoodsRange b) {
    if (a == null && b == null) {
      return null;
    } else if (a != null && b != null) {
      GoodsRange r = new GoodsRange();
      r.getGoods().addAll(a.getGoods());
      r.getGoods().addAll(b.getGoods());
      r.getCategories().addAll(a.getCategories());
      r.getCategories().addAll(b.getCategories());
      r.getBrands().addAll(a.getBrands());
      r.getBrands().addAll(b.getBrands());
      r.setAllGoods(
          r.getGoods().isEmpty() && r.getCategories().isEmpty() && r.getBrands().isEmpty());
      return r;
    } else if (a != null) {
      return a;
    } else {
      return b;
    }
  }
}
