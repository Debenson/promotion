/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	TimeRangeCondition.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月13日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.condition;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 范围促销条件。
 * 
 * <br>
 * 指数值满足一定的范围 [begin, end)
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public abstract class RangeCondition<T> extends AbstractCondition {
  private static final long serialVersionUID = -8929607858189204236L;

  private T begin;
  private T end;
  private Boolean beginEqulas = true;
  private Boolean endEquals = false;

  public RangeCondition() {
  }

  public RangeCondition(T begin, T end) {
    this.begin = begin;
    this.end = end;
  }

  public RangeCondition(T begin, T end, boolean beginEqulas, boolean endEquals) {
    this.begin = begin;
    this.end = end;
    this.beginEqulas = beginEqulas;
    this.endEquals = endEquals;
  }

  /** 起始值 */
  public T getBegin() {
    return begin;
  }

  public void setBegin(T begin) {
    this.begin = begin;
  }

  /** 结束值 */
  public T getEnd() {
    return end;
  }

  public void setEnd(T end) {
    this.end = end;
  }

  public Boolean isBeginEqulas() {
    return beginEqulas;
  }

  public void setBeginEqulas(Boolean beginEqulas) {
    this.beginEqulas = beginEqulas;
  }

  public Boolean isEndEquals() {
    return endEquals;
  }

  public void setEndEquals(Boolean endEquals) {
    this.endEquals = endEquals;
  }

}
