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

import java.math.BigDecimal;

import com.gomore.experiment.promotion.engine.ConditionResult;
import com.gomore.experiment.promotion.engine.IsCaculator;
import com.gomore.experiment.promotion.model.Context;
import com.gomore.experiment.promotion.model.condition.mbr.MemberScoreCondition;
import com.gomore.experiment.promotion.model.condition.step.StepInfo;
import com.gomore.experiment.promotion.model.condition.step.StepType;
import com.gomore.experiment.promotion.service.bean.Member;

/**
 * 会员等级促销条件计算器
 * 
 * @author Debenson
 * @since 0.1
 */
@IsCaculator
public class MemberScoreCaculator extends CaculatorSupport<MemberScoreCondition> {

  public MemberScoreCaculator() {
    super(MemberScoreCondition.class);
  }

  @Override
  public ConditionResult accept(MemberScoreCondition condition, Context context) {
    final Member mbr = getMember(context);
    boolean accept = AlgorithmCalculator.compare(mbr.getScore(), condition.getTotal(),
        condition.getComparison());
    final StepInfo stepInfo = condition.getStepInfo();
    if (!accept || stepInfo == null || StepType.NONE.equals(stepInfo.getType())) {
      // 如果不接受促销条件或不计算步进，则直接返回结果
      return ConditionResult.accept(accept);
    }

    // 计算步进。
    BigDecimal step = calcStep(mbr.getScore(), condition.getTotal(), stepInfo);
    return ConditionResult.acceptStep(step);
  }

}
