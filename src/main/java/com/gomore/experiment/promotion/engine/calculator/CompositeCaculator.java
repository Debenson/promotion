/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	BrandCaculator.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月23日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine.calculator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gomore.experiment.promotion.engine.ConditionResult;
import com.gomore.experiment.promotion.engine.IsCaculator;
import com.gomore.experiment.promotion.engine.PromotionEngine;
import com.gomore.experiment.promotion.model.Context;
import com.gomore.experiment.promotion.model.condition.CompositeCondition;
import com.gomore.experiment.promotion.model.condition.Condition;

/**
 * 组合促销条件计算器。
 * 
 * @author Debenson
 * @since 0.1
 */
@IsCaculator
@SuppressWarnings("rawtypes")
public class CompositeCaculator extends CaculatorSupport<CompositeCondition> {

  @Autowired
  private PromotionEngine engine;

  public CompositeCaculator() {
    super(CompositeCondition.class);
  }

  @Override
  public ConditionResult accept(CompositeCondition condition, Context context) {
    @SuppressWarnings("unchecked")
    List<Condition> subConditions = condition.getConditions();

    // 如果没有条件，则认为无条件成立
    if (subConditions.isEmpty()) {
      return ConditionResult.accept(true);
    }

    // 先计算第一个条件
    ConditionResult result = engine.accept(subConditions.get(0), context);
    // 因为要计算生效的商品范围，这里就不能再短路了
    // if (isShortOut(result, condition.getLogical())) {
    // return result;
    // }

    // 再计算剩下条件
    for (int i = 1; i < subConditions.size(); ++i) {
      Condition subCond = subConditions.get(i);

      // 计算下一个表达式
      ConditionResult nextResult = engine.accept(subCond, context);

      // 合并计算结果
      result = result.merge(nextResult, condition.getLogical());

      // // 判断是否可以短路提前结束计算
      // if (isShortOut(result, condition.getLogical())) {
      // return result;
      // }

      // 否则继续计算下一个条件
    }

    return result;
  }

  // /**
  // * 判断是否可以短路退出。
  // *
  // * @param result
  // * @param logical
  // * @return
  // */
  // private boolean isShortOut(ConditionResult result, Logical logical) {
  // // 只有与运算才支持短路。 或运算因为要计算步进值所以不能短路
  // return Logical.and.equals(logical) && !result.isAccept();
  // }

}
