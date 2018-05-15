/**
 * 版权所有(C)，上海勾芒信息科技，2017，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	StepCondition.java
 * 模块说明：	
 * 修改历史：
 * 2017年3月15日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.condition.step;

import com.gomore.experiment.promotion.model.condition.Condition;

/**
 * 支持叠加的促销条件。
 * 
 * @author Debenson
 * @since 0.1
 */
public interface StepCondition extends Condition {

  /**
   * 条件的叠加信息。
   * 
   * @return
   */
  public StepInfo getStepInfo();

}
