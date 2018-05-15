/**
 * 版权所有(C)，上海勾芒信息科技，2017，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	StepInfo.java
 * 模块说明：	
 * 修改历史：
 * 2017年3月14日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.condition.step;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * 促销条件的叠加信息。
 * 
 * @author Debenson
 * @since 0.1
 */
public class StepInfo implements Serializable {
  private static final long serialVersionUID = 7502272145285508417L;

  private StepType type;
  private List<Stair> stairSteps = Lists.newArrayList();

  /**
   * 叠加类型。
   * 
   * @return
   */
  public StepType getType() {
    return type;
  }

  public void setType(StepType type) {
    this.type = type;
  }

  /**
   * 阶梯叠加。仅当 {@link #getType()} == {@link StepType#STAIR}时有效。
   * 
   * @return
   */
  public List<Stair> getStairSteps() {
    return stairSteps;
  }

  public void setStairSteps(List<Stair> stairSteps) {
    this.stairSteps = stairSteps;
  }

}
