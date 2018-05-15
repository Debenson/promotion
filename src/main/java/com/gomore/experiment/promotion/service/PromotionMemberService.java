/**
 * 版权所有(C)，上海勾芒信息科技，2017，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	MemberService.java
 * 模块说明：	
 * 修改历史：
 * 2017年8月28日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.service;

import java.util.List;

import com.gomore.experiment.promotion.common.UCN;

/**
 * 会员相关
 * 
 * @author Debenson
 * @since 0.1
 */
public interface PromotionMemberService {

  /**
   * 取得所有会员等级，等级从低到高排列
   */
  public List<UCN> getGrades();

}
