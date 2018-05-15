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

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 时间范围促销条件。
 * 
 * <br>
 * 指时间满足一定的范围 [begin, end)
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public class TimeRangeCondition extends RangeCondition<Date> {
  private static final long serialVersionUID = -8929607858189204236L;

  public static final String CTYPE = "timeRangeCondition";

  public TimeRangeCondition() {
    super();
  }

  public TimeRangeCondition(Date begin, Date end) {
    super(begin, end, true, false);
  }

  @Override
  public String getType() {
    return CTYPE;
  }

  /** 起始时间（含等于） */
  @NotNull
  @Future
  @Override
  public Date getBegin() {
    return super.getBegin();
  }

  /** 截止时间 */
  @Override
  public Date getEnd() {
    return super.getEnd();
  }

}
