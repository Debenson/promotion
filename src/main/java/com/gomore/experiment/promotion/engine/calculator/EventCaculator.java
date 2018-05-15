/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	EventConditionCaculator.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月23日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine.calculator;

import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.gomore.experiment.promotion.engine.ConditionResult;
import com.gomore.experiment.promotion.engine.IsCaculator;
import com.gomore.experiment.promotion.model.Context;
import com.gomore.experiment.promotion.model.condition.event.EventCondition;
import com.gomore.experiment.promotion.model.condition.event.EventType;
import com.gomore.experiment.promotion.model.condition.event.PromotionEvent;

/**
 * 交易时间促销计算器。
 * 
 * @author Debenson
 * @since 0.1
 */
@IsCaculator
public class EventCaculator extends CaculatorSupport<EventCondition> {

  public EventCaculator() {
    super(EventCondition.class);
  }

  @Override
  @SuppressWarnings("rawtypes")
  public ConditionResult accept(EventCondition condition, Context context) {
    final PromotionEvent event = getEvent(context);
    if (event == null || condition.getEvent() == null) {
      return ConditionResult.accept(false);
    }

    // 事件类型必须一致
    if (!Objects.equals(condition.getEvent().getType(), event.getType())) {
      return ConditionResult.accept(false);
    }

    boolean accept = true;
    if (EventType.MBR_SIGNIN.equals(event.getType())) {
      // 如果是连续签到，需要特殊处理
      accept = (Integer) event.getParams() >= (Integer) condition.getEvent().getParams();

    } else if (EventType.JOIN_ACTIVITY.equals(event.getType())) {
      // 任意活动或者指定的活动
      Map params = (Map) condition.getEvent().getParams();
      final String activityId = (params == null ? null : (String) params.get("uuid"));
      accept = (activityId == null || StringUtils.isBlank(activityId))
          || Objects.equals(activityId, event.getParams());

    } else if (EventType.JOIN_PAPER.equals(event.getType())) {
      // 任意调查问卷或者指定的调查问卷
      Map params = (Map) condition.getEvent().getParams();
      final String paperId = (params == null ? null : (String) params.get("uuid"));
      accept = (paperId == null || StringUtils.isBlank(paperId))
          || Objects.equals(paperId, event.getParams());
    }
    return ConditionResult.accept(accept);
  }

}
