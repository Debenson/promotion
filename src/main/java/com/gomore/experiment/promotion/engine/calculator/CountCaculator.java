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
import com.gomore.experiment.promotion.model.condition.CountCondition;
import com.gomore.experiment.promotion.model.condition.step.StepInfo;
import com.gomore.experiment.promotion.model.condition.step.StepType;

/**
 * 计算订单商品数量是否满足条件。
 * 
 * @author Debenson
 * @since 0.1
 */
@IsCaculator
public class CountCaculator extends CaculatorSupport<CountCondition> {

  public CountCaculator() {
    super(CountCondition.class);
  }

  @Override
  public ConditionResult accept(CountCondition condition, Context context) {
    Map<String, Integer> goodsCountMap = safeGetGoodsCountMap(context);
    int orderTotal = 0;
    for (Integer count : goodsCountMap.values()) {
      orderTotal += count;
    }

    boolean accept = AlgorithmCalculator.compare(BigDecimal.valueOf(orderTotal),
        condition.getCount(), condition.getComparison());
    final StepInfo stepInfo = condition.getStepInfo();
    if (!accept || stepInfo == null || StepType.NONE.equals(stepInfo.getType())) {
      // 如果不接受促销条件或不计算步进，则直接返回结果
      return ConditionResult.accept(accept);
    }

    // 计算步进。
    BigDecimal step = calcStep(BigDecimal.valueOf(orderTotal), condition.getCount(), stepInfo);
    return ConditionResult.acceptStep(step);
  }

}
