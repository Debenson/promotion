/**
 * 版权所有(C)，上海勾芒信息科技，2017，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	AbstractCondition.java
 * 模块说明：	
 * 修改历史：
 * 2017年3月14日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.condition.step;

import com.gomore.experiment.promotion.model.condition.AbstractCondition;

/**
 * @author Debenson
 * @since 0.1
 */
public abstract class AbstractStepCondition extends AbstractCondition implements StepCondition {
  private static final long serialVersionUID = -2882313020616168712L;

  private StepInfo stepInfo;

  @Override
  public StepInfo getStepInfo() {
    return stepInfo;
  }

  public void setStepInfo(StepInfo stepInfo) {
    this.stepInfo = stepInfo;
  }

}
