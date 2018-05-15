/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	CountCaculator.java
 * 模块说明：	
 * 修改历史：
 * 2016年11月2日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine.calculator;

import java.math.BigDecimal;
import java.util.Map;

import com.gomore.experiment.promotion.engine.ConditionResult;
import com.gomore.experiment.promotion.engine.IsCaculator;
import com.gomore.experiment.promotion.model.Context;
import com.gomore.experiment.promotion.model.condition.AmountCondition;
import com.gomore.experiment.promotion.model.condition.step.StepInfo;
import com.gomore.experiment.promotion.model.condition.step.StepType;

/**
 * 计算订单商品金额是否满足条件。
 * 
 * @author Debenson
 * @since 0.1
 */
@IsCaculator
public class AmountCaculator extends CaculatorSupport<AmountCondition> {

  public AmountCaculator() {
    super(AmountCondition.class);
  }

  @Override
  public ConditionResult accept(AmountCondition condition, Context context) {
    Map<String, BigDecimal> goodsAmountMap = safeGetGoodsAmountMap(context);
    BigDecimal orderTotal = BigDecimal.ZERO;
    for (BigDecimal total : goodsAmountMap.values()) {
      orderTotal = orderTotal.add(total);
    }

    boolean accept = AlgorithmCalculator.compare(orderTotal, condition.getTotal(),
        condition.getComparison());
    final StepInfo stepInfo = condition.getStepInfo();
    if (!accept || stepInfo == null || StepType.NONE.equals(stepInfo.getType())) {
      // 如果不接受促销条件或不计算步进，则直接返回结果
      return ConditionResult.accept(accept);
    }

    // 计算步进。
    BigDecimal step = calcStep(orderTotal, condition.getTotal(), stepInfo);
    return ConditionResult.acceptStep(step);
  }

}
