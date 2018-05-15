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

import org.springframework.beans.factory.annotation.Autowired;

import com.gomore.experiment.promotion.common.HasUCN;
import com.gomore.experiment.promotion.engine.ConditionResult;
import com.gomore.experiment.promotion.engine.IsCaculator;
import com.gomore.experiment.promotion.model.Context;
import com.gomore.experiment.promotion.model.condition.GoodsRange;
import com.gomore.experiment.promotion.model.condition.goods.CategoryCondition;
import com.gomore.experiment.promotion.service.PromotionCategoryService;

/**
 * 商品品牌促销计算器。
 * 
 * @author Debenson
 * @since 0.1
 */
@IsCaculator
public class CategoryCaculator extends CaculatorSupport<CategoryCondition> {

  @Autowired
  private PromotionCategoryService cateService;

  public CategoryCaculator() {
    super(CategoryCondition.class);
  }

  @Override
  public ConditionResult accept(CategoryCondition condition, Context context) {
    if (condition.acceptAny()) {
      ConditionResult cr = ConditionResult.accept(true);
      cr.setGoodsRange(GoodsRange.allGoods());
      return cr;
    }

    List<HasUCN> categories = safeGetGoodsCategories(context);
    for (HasUCN cat : categories) {
      // 只要是商品的分类等于促销条件中的分类或是其下级分类，都认为是匹配的。
      if (cateService.categoryBelongTo(cat, condition.getCategory())) {
        ConditionResult cr = ConditionResult.accept(true);
        // 添加商品分类范围
        cr.setGoodsRange(GoodsRange.category(condition.getCategory()));
        return cr;
      }
    }
    return ConditionResult.accept(false);
  }

}
