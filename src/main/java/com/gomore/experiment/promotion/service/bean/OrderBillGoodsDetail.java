/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	OrderBill.java
 * 模块说明：	
 * 修改历史：
 * 2016年11月7日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.service.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.gomore.experiment.promotion.common.HasUCN;

/**
 * 订单明细。
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public interface OrderBillGoodsDetail extends Serializable {

  /**
   * 商品
   * 
   * @return
   */
  public HasUCN getGoods();

  /**
   * 商品类别。
   * 
   * @return
   */
  public List<HasUCN> getCategories();

  /**
   * 商品品牌。
   * 
   * @return
   */
  public HasUCN getBrand();

  /**
   * 售价
   * 
   * @return
   */
  public BigDecimal getPrice();

  /**
   * 商品数量。
   * 
   * @return
   */
  public Integer getCount();

}
