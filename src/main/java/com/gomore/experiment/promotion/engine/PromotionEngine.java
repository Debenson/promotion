/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	PromotionEngine.java
 * 模块说明：	
 * 修改历史：
 * 2016年8月9日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine;

import com.gomore.experiment.promotion.bill.bean.PromotionBill;
import com.gomore.experiment.promotion.model.Context;
import com.gomore.experiment.promotion.model.PromotionException;
import com.gomore.experiment.promotion.model.action.Action;
import com.gomore.experiment.promotion.model.condition.Condition;
import com.gomore.experiment.promotion.service.bean.OrderBill;

/**
 * 促销引擎。
 * 
 * @author Debenson
 * @since 0.1
 */
public interface PromotionEngine {

  /**
   * 判断指定的促销条件是否成立。
   * 
   * @param condition
   *          促销条件，非空。
   * @param context
   *          促销上下文。
   * @return 促销条件是否成立及其步进信息。
   */
  public ConditionResult accept(Condition condition, Context context);

  /**
   * 计算促销结果。
   * 
   * @param context
   *          促销上下文。
   * @param promBills
   *          促销单，如果不指定就取当前组织生效的所有促销单
   * @return 返回促销动作结果，结果按照优先级排列。
   * @throws PromotionException
   *           计算失败会抛出此异常。
   */
  public PromotionResult execute(Context context, PromotionBill... promBills)
      throws PromotionException;

  /**
   * 计算促销结果。
   * 
   * @param bill
   *          业务单据。
   * @param promBills
   *          促销单，如果不指定就取当前组织生效的所有促销单
   * @return 返回促销动作结果，结果按照优先级排列。
   * @throws PromotionException
   *           计算失败会抛出此异常。
   */
  public PromotionResult execute(OrderBill bill, PromotionBill... promBills)
      throws PromotionException;

  /**
   * 是否有指定的促销结果
   * 
   * @param context
   *          促销上下文。
   * @param action
   *          促销结果
   * @param promBills
   *          促销单，如果不指定就取当前组织生效的所有促销单
   * @return
   * @throws PromotionException
   *           计算失败会抛出此异常。
   */
  public boolean containsAction(Context context, Action action, PromotionBill... promBills)
      throws PromotionException;

  /**
   * 是否有指定的促销结果
   * 
   * @param bill
   *          业务单据。
   * @param action
   *          促销结果
   * @param promBills
   *          促销单，如果不指定就取当前组织生效的所有促销单
   * @return
   * @throws PromotionException
   *           计算失败会抛出此异常。
   */
  public boolean containsAction(OrderBill bill, Action action, PromotionBill... promBills)
      throws PromotionException;

}
