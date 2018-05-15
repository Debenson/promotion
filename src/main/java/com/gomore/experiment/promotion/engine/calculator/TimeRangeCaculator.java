/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	TimeRangeConditionCaculator.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月23日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine.calculator;

import java.util.Date;

import com.gomore.experiment.promotion.engine.ConditionResult;
import com.gomore.experiment.promotion.engine.IsCaculator;
import com.gomore.experiment.promotion.model.Context;
import com.gomore.experiment.promotion.model.condition.TimeRangeCondition;

/**
 * 交易时间促销计算器。
 * 
 * @author Debenson
 * @since 0.1
 */
@IsCaculator
public class TimeRangeCaculator extends CaculatorSupport<TimeRangeCondition> {

  public TimeRangeCaculator() {
    super(TimeRangeCondition.class);
  }

  @Override
  public ConditionResult accept(TimeRangeCondition condition, Context context) {
    final Date tradeTime = safeGetTradeTime(context);

    boolean accept = true;
    if (condition.getBegin() == null) {
      // 起始时间不允许为null
      accept = false;
    } else if (tradeTime.before(condition.getBegin())) {
      accept = false;
    } else if (condition.getEnd() != null && tradeTime.after(condition.getEnd())) {
      accept = false;
    } else {
      // getEnd() == null表示长时间有效
      accept = true;
    }
    return ConditionResult.accept(accept);
  }

}
