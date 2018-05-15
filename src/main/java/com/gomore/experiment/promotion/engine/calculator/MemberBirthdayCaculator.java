/**
 * 版权所有(C)，上海勾芒信息科技，2017，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	MemberScoreCaculator.java
 * 模块说明：	
 * 修改历史：
 * 2017年8月28日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine.calculator;

import java.util.Date;

import com.gomore.experiment.promotion.engine.ConditionResult;
import com.gomore.experiment.promotion.engine.IsCaculator;
import com.gomore.experiment.promotion.model.Context;
import com.gomore.experiment.promotion.model.condition.mbr.MemberBirthdayCondition;
import com.gomore.experiment.promotion.service.bean.Member;

/**
 * 会员生日促销条件计算器
 * 
 * @author Debenson
 * @since 0.1
 */
@IsCaculator
public class MemberBirthdayCaculator extends CaculatorSupport<MemberBirthdayCondition> {

  public MemberBirthdayCaculator() {
    super(MemberBirthdayCondition.class);
  }

  @Override
  public ConditionResult accept(MemberBirthdayCondition condition, Context context) {
    final Member mbr = getMember(context);
    if (mbr == null || mbr.getBirthday() == null) {
      return ConditionResult.accept(false);
    }

    final Date now = new Date();
    boolean accept = false;
    if (condition.getIsBirthday() != null && condition.getIsBirthday()) {
      accept = isSameDayOfMonth(now, mbr.getBirthday());
    } else {
      accept = !isSameDayOfMonth(now, mbr.getBirthday());
    }
    return ConditionResult.accept(accept);
  }

  @SuppressWarnings("deprecation")
  private boolean isSameDayOfMonth(Date a, Date b) {
    return a != null && b != null && (a.getMonth() == b.getMonth()) && (a.getDay() == b.getDay());
  }

}
