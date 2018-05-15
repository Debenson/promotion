/**
 * 版权所有(C)，上海勾芒信息科技，2018，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	Operator.java
 * 模块说明：	
 * 修改历史：
 * 2018年3月15日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.service.bean;

import com.gomore.experiment.promotion.common.UCN;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 操作人
 * 
 * @author Debenson
 * @since 0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Operator extends UCN {
  private static final long serialVersionUID = 7903925223348065611L;

  /**
   * 操作人所属组织ID
   */
  private String orgId;

}
