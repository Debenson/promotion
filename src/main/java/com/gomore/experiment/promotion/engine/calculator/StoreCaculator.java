/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	StoreCaculator.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月23日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine.calculator;

import org.springframework.beans.factory.annotation.Autowired;

import com.gomore.experiment.promotion.common.HasUCN;
import com.gomore.experiment.promotion.common.UCN;
import com.gomore.experiment.promotion.engine.ConditionResult;
import com.gomore.experiment.promotion.engine.IsCaculator;
import com.gomore.experiment.promotion.model.Context;
import com.gomore.experiment.promotion.model.condition.StoreCondition;
import com.gomore.experiment.promotion.service.PromotionStoreService;
import com.gomore.experiment.promotion.service.bean.OrderBill;

/**
 * 门店促销条件计算器。
 * 
 * @author Debenson
 * @since 0.1
 */
@IsCaculator
public class StoreCaculator extends CaculatorSupport<StoreCondition> {

  @Autowired
  private PromotionStoreService storeService;

  public StoreCaculator() {
    super(StoreCondition.class);
  }

  @Override
  public ConditionResult accept(StoreCondition condition, Context context) {
    final OrderBill order = getOrder(context);
    if (condition.acceptAny()) {
      return ConditionResult.accept(true);
    }

    final HasUCN destStore = order.getStore();
    if (destStore != null) {
      for (UCN store : condition.getStores()) {
        if (storeService.storeBelongTo(destStore, store)) {
          return ConditionResult.accept(true);
        }
      }
    }
    return ConditionResult.accept(false);
  }

}
