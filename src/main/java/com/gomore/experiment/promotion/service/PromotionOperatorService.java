/**
 * 版权所有(C)，上海勾芒信息科技，2018，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	UserService.java
 * 模块说明：	
 * 修改历史：
 * 2018年3月15日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.service;

import com.gomore.experiment.promotion.service.bean.Operator;

/**
 * 操作人服务
 * 
 * @author Debenson
 * @since 0.1
 */
public interface PromotionOperatorService {

  /**
   * 取得当前操作人
   * 
   * @return
   */
  Operator getCurrentOperator();

}
