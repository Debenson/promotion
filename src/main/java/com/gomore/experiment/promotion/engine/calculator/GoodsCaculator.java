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
import com.gomore.experiment.promotion.model.condition.goods.GoodsCondition;

/**
 * 商品品牌促销计算器。
 * 
 * @author Debenson
 * @since 0.1
 */
@IsCaculator
public class GoodsCaculator extends CaculatorSupport<GoodsCondition> {

  public GoodsCaculator() {
    super(GoodsCondition.class);
  }

  @Override
  public ConditionResult accept(GoodsCondition condition, Context context) {
    if (condition.acceptAny()) {
      ConditionResult cr = ConditionResult.accept(true);
      cr.setGoodsRange(GoodsRange.allGoods());
      return cr;
    }

    List<String> goodsIds = safeGetGoodsIds(context);
    boolean accept = goodsIds.contains(condition.getGoods().getUuid());
    ConditionResult cr = ConditionResult.accept(accept);
    if (accept) {
      // 添加生效的商品范围
      cr.setGoodsRange(GoodsRange.goods(condition.getGoods()));
    }
    return cr;
  }

}
