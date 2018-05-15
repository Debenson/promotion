/**
 * 版权所有(C)，上海勾芒信息科技，2017，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	Event.java
 * 模块说明：	
 * 修改历史：
 * 2017年8月28日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.condition.event;

import java.io.Serializable;

/**
 * 促销事件
 * 
 * @author Debenson5
 * @since 0.1
 */
public class PromotionEvent implements Serializable {
  private static final long serialVersionUID = 5535648349380597950L;

  private EventType type;
  private Object params;

  /**
   * 注册事件
   * 
   * @param mbrId
   *          会员id
   * @return
   */
  public static PromotionEvent register(String mbrId) {
    PromotionEvent event = new PromotionEvent(EventType.MBR_REGISTER, mbrId);
    return event;
  }

  /**
   * 完善会员资料事件
   * 
   * @param mbrId
   *          会员id
   * @return
   */
  public static PromotionEvent completeMbrInfo(String mbrId) {
    PromotionEvent event = new PromotionEvent(EventType.COMPLETE_MBR_INFO, mbrId);
    return event;
  }

  /**
   * 会员签到事件
   * 
   * @param continueDays
   *          连续签到天数
   * @return
   */
  public static PromotionEvent signin(int continueDays) {
    PromotionEvent event = new PromotionEvent(EventType.MBR_SIGNIN, continueDays);
    return event;
  }

  /**
   * 会员分享事件
   * 
   * @return
   */
  public static PromotionEvent share() {
    PromotionEvent event = new PromotionEvent(EventType.WX_SHARE);
    return event;
  }

  /**
   * 参加活动事件
   * 
   * @param activity
   *          活动id，为null表示任意活动
   * @return
   */
  public static PromotionEvent joinActivity(String activity) {
    PromotionEvent event = new PromotionEvent(EventType.JOIN_ACTIVITY, activity);
    return event;
  }

  /**
   * 参加调查问卷事件
   * 
   * @param paper
   *          调查问卷id，为null表示任意调查问卷
   * @return
   */
  public static PromotionEvent joinPaper(String paper) {
    PromotionEvent event = new PromotionEvent(EventType.JOIN_PAPER, paper);
    return event;
  }

  /**
   * 邀请会员事件
   *
   * @param introducerId
   *          邀请人ID
   * @return
   */
  public static PromotionEvent inviteMember(String introducerId) {
    PromotionEvent event = new PromotionEvent(EventType.INVITE_MEMBER, introducerId);
    return event;
  }

  public PromotionEvent() {
  }

  public PromotionEvent(EventType type) {
    this.type = type;
  }

  public PromotionEvent(EventType type, Object params) {
    this.type = type;
    this.params = params;
  }

  /** 事件类型 */
  public void setType(EventType type) {
    this.type = type;
  }

  public void setParams(Object params) {
    this.params = params;
  }

  /** 参数，可空 */
  public Object getParams() {
    return params;
  }

  public EventType getType() {
    return type;
  }

}
