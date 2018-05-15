/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	BrandCaculator.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月23日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine.calculator;

import java.util.List;

import com.gomore.experiment.promotion.engine.ConditionResult;
import com.gomore.experiment.promotion.engine.IsCaculator;
import com.gomore.experiment.promotion.model.Context;
import com.gomore.experiment.promotion.model.condition.GoodsRange;
import com.gomore.experiment.promotion.model.condition.goods.BrandCondition;

/**
 * 商品品牌促销计算器。
 * 
 * @author Debenson
 * @since 0.1
 */
@IsCaculator
public class BrandCaculator extends CaculatorSupport<BrandCondition> {

  public BrandCaculator() {
    super(BrandCondition.class);
  }

  @Override
  public ConditionResult accept(BrandCondition condition, Context context) {
    if (condition.acceptAny()) {
      ConditionResult cr = ConditionResult.accept(true);
      cr.setGoodsRange(GoodsRange.allGoods());
      return cr;
    }

    List<String> brands = safeGetGoodsBrands(context);
    boolean accept = brands.contains(condition.getBrand().getUuid());
    final ConditionResult cr = ConditionResult.accept(accept);
    if (accept) {
      // 添加商品品牌范围条件
      cr.setGoodsRange(GoodsRange.brand(condition.getBrand()));
    }
    return cr;
  }

}
