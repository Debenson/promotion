/**
 * 2015，thor.com，all rights reserved。
 * 
 * Project：		thor-common
 * Filename：	QueryResultPaging.java
 * Created：		2015年3月14日
 * Creator：		thor
 */
package com.gomore.experiment.promotion.bean;

import java.io.Serializable;

/**
 * @author tom
 * 
 */
public class QueryResultPaging implements Serializable {
  private static final long serialVersionUID = 1044822353881303949L;

  private int page = 0;
  private int pageSize = 0;
  private int pageCount = 0;
  private long recordCount = 0;

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

  public int getPageCount() {
    return pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }

  public long getRecordCount() {
    return recordCount;
  }

  public void setRecordCount(long recordCount) {
    this.recordCount = recordCount;
  }
}
