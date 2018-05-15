/**
 * 版权所有(C)，上海勾芒信息科技，2017，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	EventCondition.java
 * 模块说明：	
 * 修改历史：
 * 2017年8月25日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.condition.event;

import com.gomore.experiment.promotion.model.condition.AbstractCondition;

/**
 * 事件促销条件 <br>
 * 事件条件是指， 促销的发生是由一种预定义的事件触发的， 如：注册会员、会员签到等。
 * 
 * @author Debenson
 * @since 0.1
 */
public class EventCondition extends AbstractCondition {
  private static final long serialVersionUID = -166243898957352173L;

  public static final String CTYPE = "eventCondition";

  private PromotionEvent event;

  public EventCondition() {
    super();
  }

  public EventCondition(PromotionEvent event) {
    super();
    this.event = event;
  }

  @Override
  public String getType() {
    return CTYPE;
  }

  /** 事件 */
  public PromotionEvent getEvent() {
    return event;
  }

  public void setEvent(PromotionEvent event) {
    this.event = event;
  }

}
