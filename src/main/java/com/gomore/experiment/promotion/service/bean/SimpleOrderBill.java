/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	wxmall
 * 文件名：	OrderBillImpl.java
 * 模块说明：	
 * 修改历史：
 * 2016年11月7日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.service.bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.gomore.experiment.promotion.common.UCN;
import com.gomore.experiment.promotion.model.condition.event.PromotionEvent;
import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 订单。
 * 
 * @author Debenson
 * @since 0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SimpleOrderBill implements OrderBill {
  private static final long serialVersionUID = -1814508530770547750L;

  private String id = UUID.randomUUID().toString().replace("-", "");
  private String ownerOrg;
  private Date tradeTime = new Date();
  private Member consumer;
  private UCN store;
  private UCN business;
  private UCN salesman;
  private PromotionEvent event;
  private String remark;
  private List<OrderBillGoodsDetail> goodsDetails = Lists.newArrayList();

}
