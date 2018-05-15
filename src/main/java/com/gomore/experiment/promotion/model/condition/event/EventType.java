package com.gomore.experiment.promotion.model.condition.event;

/**
 * 事件类型
 * 
 * @author Debenson
 * @since 0.1
 */
public enum EventType {

  /** 注册会员 */
  MBR_REGISTER,
  
  /** 邀请会员 */
  INVITE_MEMBER,

  /** 完善会员资料 */
  COMPLETE_MBR_INFO,

  /** 会员签到 */
  MBR_SIGNIN,

  /** 微信分享 */
  WX_SHARE,

  /** 参加活动 */
  JOIN_ACTIVITY,

  /** 参加调查问卷 */
  JOIN_PAPER;

}
