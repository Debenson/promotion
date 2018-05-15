/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	wxmall
 * 文件名：	OrderBillGoodsDtlImpl.java
 * 模块说明：	
 * 修改历史：
 * 2016年11月7日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.service.bean;

import java.math.BigDecimal;
import java.util.List;

import com.gomore.experiment.promotion.common.HasUCN;
import com.gomore.experiment.promotion.common.UCN;

/**
 * 订单商品明细行。
 * 
 * @author Debenson
 * @since 0.1
 */
public class SimpleOrderBillGoodsDetail implements OrderBillGoodsDetail {
  private static final long serialVersionUID = 1680119679078632743L;

  private UCN goods;
  private List<UCN> categories;
  private UCN brand;
  private BigDecimal price;
  private Integer count;

  @Override
  public HasUCN getGoods() {
    return goods;
  }

  @SuppressWarnings({
      "unchecked", "rawtypes" })
  @Override
  public List<HasUCN> getCategories() {
    return (List) categories;
  }

  @Override
  public HasUCN getBrand() {
    return brand;
  }

  @Override
  public BigDecimal getPrice() {
    return price;
  }

  @Override
  public Integer getCount() {
    return count;
  }

  public void setGoods(UCN goods) {
    this.goods = goods;
  }

  public void setCategories(List<UCN> categories) {
    this.categories = categories;
  }

  public void setBrand(UCN brand) {
    this.brand = brand;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

}
