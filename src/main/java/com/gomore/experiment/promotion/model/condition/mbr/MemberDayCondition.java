/**
 * 版权所有(C)，上海勾芒信息科技，2018，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	MemberDayCondition.java
 * 模块说明：	
 * 修改历史：
 * 2018年5月2日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.condition.mbr;

import java.io.Serializable;
import java.util.List;

import com.gomore.experiment.promotion.model.condition.AbstractCondition;

/**
 * 会员日促销条件
 * 
 * @author Debenson
 * @since 0.1
 */
public class MemberDayCondition extends AbstractCondition {
  private static final long serialVersionUID = 5504301483118803258L;

  public static final String CTYPE = "memberDayCondition";

  /**
   * 会员日
   */
  private List<MemberDay> days;

  @Override
  public String getType() {
    return CTYPE;
  }

  /**
   * 会员日
   * 
   * @return
   */
  public List<MemberDay> getDays() {
    return days;
  }

  public void setDays(List<MemberDay> days) {
    this.days = days;
  }

  /**
   * 会员日
   * 
   * @author Debenson
   * @since 0.1
   */
  public static class MemberDay implements Serializable {
    private static final long serialVersionUID = -9147345244662870785L;

    /**
     * 会员日期单位
     */
    private DateUnit unit;

    /**
     * 会员日 <br>
     * 如果dateUnit==DAY_OF_WEEK，则取值: 1, 2, 3, 4, 5, 6, 7; <br>
     * 如果dateUnit==DAY_OF_MONTH，则取值: 1, 2, 3...31 <br>
     * 如果dateUnit==MONTH_OF_YEAR，则取值: 1, 2, 3...12 <br>
     * 如果dateUnit==DAY_OF_YEAR，则取值: MMdd，如0201(二月一号),0305(三月五号)
     */
    private String value;

    public DateUnit getUnit() {
      return unit;
    }

    public void setUnit(DateUnit unit) {
      this.unit = unit;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }

  }

  /**
   * 会员日期单位
   * 
   * @author Debenson
   * @since 0.1
   */
  public static enum DateUnit {

    /**
     * 每周
     */
    DAY_OF_WEEK,

    /**
     * 每天
     */
    DAY_OF_MONTH,

    /**
     * 每月
     */
    MONTH_OF_YEAR,

    /**
     * 每年的月/日
     */
    DATE_OF_YEAR

  }

}
