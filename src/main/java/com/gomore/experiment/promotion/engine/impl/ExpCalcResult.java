/**
 * 版权所有(C)，上海勾芒信息科技，2018，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	ExpResult.java
 * 模块说明：	
 * 修改历史：
 * 2018年5月2日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.gomore.experiment.promotion.engine.ConditionResult;
import com.gomore.experiment.promotion.model.action.Action;
import com.gomore.experiment.promotion.model.action.GoodsRangeAction;
import com.google.common.collect.Lists;

import lombok.Data;

/**
 * 表达式计算结果
 * 
 * @author Debenson
 * @since 0.1
 */
@Data
public class ExpCalcResult implements Serializable {
  private static final long serialVersionUID = -7874378001748021215L;

  private ConditionResult result;
  private List<Action> actions = Lists.newArrayList();

  /**
   * 
   */
  public ExpCalcResult() {
  }

  /**
   * @param result
   */
  public ExpCalcResult(ConditionResult result) {
    this(result, new ArrayList<Action>());
  }

  /**
   * @param result
   *          表达式计算结果
   * @param actions
   *          促销结果
   */
  public ExpCalcResult(ConditionResult result, List<Action> actions) {
    this.result = result;
    this.actions = actions;
    if (actions != null && result != null && result.getGoodsRange() != null) {
      for (Action action : actions) {
        if (action instanceof GoodsRangeAction) {
          ((GoodsRangeAction) action).setGoodsRange(result.getGoodsRange());
        }
      }
    }
  }

  /**
   * 不接受促销条件。
   * 
   * @return
   */
  public static ExpCalcResult refuse() {
    return new ExpCalcResult(ConditionResult.refuse());
  }

}