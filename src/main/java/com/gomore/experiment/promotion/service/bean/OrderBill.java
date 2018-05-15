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
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.gomore.experiment.promotion.common.HasUCN;
import com.gomore.experiment.promotion.model.condition.event.PromotionEvent;

/**
 * 促销订单。 该订单是一个抽象的概念，用来描述发生促销的上下文环境，并不与实际的交易一致。
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public interface OrderBill extends Serializable {

  /**
   * 订单标识, 不可空
   * 
   * @return
   */
  public String getId();

  /**
   * 交易时间，不可空
   * 
   * @return
   */
  public Date getTradeTime();

  /**
   * 所属组织或公司，不可空
   * 
   * @return
   */
  public String getOwnerOrg();

  /**
   * 订单的发生门店或商户，可空，空表示不限制。
   * 
   * @return
   */
  public HasUCN getStore();

  /**
   * 门店所属业态，可空，空表示不限制。
   * 
   * @return
   */
  public HasUCN getBusiness();

  /**
   * 业务员，可空。
   * 
   * @return
   */
  public HasUCN getSalesman();

  /**
   * 消费者或者会员 ，可空。
   * 
   * @return
   */
  public HasUCN getConsumer();

  /**
   * 取得商品明细行，可空。
   * 
   * @return
   */
  public List<OrderBillGoodsDetail> getGoodsDetails();

  /**
   * 取得促销事件，仅当是事件促销时才有效。 可空。
   * 
   * @return
   */
  public PromotionEvent getEvent();

  /**
   * 备注
   */
  public String getRemark();

}
