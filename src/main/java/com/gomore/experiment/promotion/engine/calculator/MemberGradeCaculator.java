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

import com.gomore.experiment.promotion.engine.ConditionResult;
import com.gomore.experiment.promotion.engine.IsCaculator;
import com.gomore.experiment.promotion.model.Context;
import com.gomore.experiment.promotion.model.condition.mbr.MemberGradeCondition;
import com.gomore.experiment.promotion.service.bean.Member;

/**
 * 会员等级促销条件计算器
 * 
 * @author Debenson
 * @since 0.1
 */
@IsCaculator
public class MemberGradeCaculator extends CaculatorSupport<MemberGradeCondition> {

  public MemberGradeCaculator() {
    super(MemberGradeCondition.class);
  }

  @Override
  public ConditionResult accept(MemberGradeCondition condition, Context context) {
    final Member mbr = getMember(context);
    if (mbr == null || mbr.getGrade() == null || condition.getGrade() == null) {
      return ConditionResult.accept(false);
    }

    // 当前会员等级大于条件值才接受促销条件
    int grade = Integer.valueOf(mbr.getGrade().getCode());
    return ConditionResult.accept(grade >= Integer.valueOf(condition.getGrade().getCode()));
  }

}
