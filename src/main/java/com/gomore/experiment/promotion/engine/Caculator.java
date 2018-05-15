/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	Caculator.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月23日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine;

import com.gomore.experiment.promotion.model.Context;
import com.gomore.experiment.promotion.model.condition.Condition;

/**
 * 促销条件计算器。
 * 
 * @author Debenson
 * @since 0.1
 */
public interface Caculator<T extends Condition> {

  /**
   * 判断是否支持计算当前促销条件。
   * 
   * @param condition
   *          促销上下文环境。
   * @return 是否支持促销。
   */
  public boolean support(T condition);

  /**
   * 计算是否接受促销。
   * 
   * @param condition
   *          促销条件， 禁止为null。
   * @param context
   *          促销上下文，禁止为null。
   * @return 促销计算结果。
   */
  public ConditionResult accept(T condition, Context context);

}
