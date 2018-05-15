/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	PagingCondition.java
 * 模块说明：	
 * 修改历史：
 * 2016年10月2日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.gomore.experiment.commons.rest.QueryOrder;
import com.gomore.experiment.commons.rest.QueryOrderDirection;

/**
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public class PagingCondition implements Serializable {
  private static final long serialVersionUID = 7289700503691332939L;

  private int page = 0;
  private int pageSize = 0;
  private List<QueryOrder> orders = new ArrayList<QueryOrder>();

  public void addOrder(String field, QueryOrderDirection direction) {
    orders.add(new QueryOrder(field, direction));
  }

  public void addOrder(String field) {
    orders.add(new QueryOrder(field));
  }

  /** 当前页，起始于0 */
  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  /** 每页记录数，0表示取全部数据 */
  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  /** 排序条件 */
  public List<QueryOrder> getOrders() {
    return orders;
  }

  public void setOrders(List<QueryOrder> orders) {
    this.orders = orders;
  }

}
