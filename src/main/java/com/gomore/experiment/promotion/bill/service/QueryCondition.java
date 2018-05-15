/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	QueryCondition.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月22日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.bill.service;

import java.io.Serializable;

/**
 * @author Debenson
 * @since 0.1
 */
public abstract class QueryCondition implements Serializable {
  private static final long serialVersionUID = 2533489900375882449L;

  private int page = 1;// 当前页
  private int pageSize = 10;// 每页显示记录数

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

}
