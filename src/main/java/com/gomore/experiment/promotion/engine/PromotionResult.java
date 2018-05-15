/**
 * 版权所有(C)，上海勾芒信息科技，2017，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	PromotionResult.java
 * 模块说明：	
 * 修改历史：
 * 2017年10月16日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine;

import java.math.BigDecimal;
import java.util.List;

import com.gomore.experiment.promotion.bill.bean.PromotionBill;
import com.gomore.experiment.promotion.model.Context;
import com.gomore.experiment.promotion.model.action.Action;

/**
 * 促销计算结果
 * 
 * @author Debenson
 * @since 0.1
 */
public interface PromotionResult {

  /**
   * 促销计算的结果集合
   * 
   * @return
   */
  public List<Action> getActions();

  /**
   * 取得指定的促销结果集合
   * 
   * @param clazz
   * @return
   */
  public List<Action> getActions(Class<? extends Action> clazz);

  /**
   * 取得总的积分数
   * 
   * @return
   */
  public BigDecimal getScore();

  /**
   * 用以计算的促销的上下文环境
   * 
   * @return
   */
  public Context getContext();

  /**
   * 取得生效的促销单
   * 
   * @return
   */
  public List<PromotionBill> getEffectiveBills();

  /**
   * 取得促销结果描述信息
   * 
   * <br>
   * 与 {@link #toString()}返回同样的结果
   * 
   * @return
   */
  public String getDescription();

}
