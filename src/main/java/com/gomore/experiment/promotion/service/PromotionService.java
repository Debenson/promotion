/**
 * 版权所有(C)，上海勾芒信息科技，2018，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	PromotionService.java
 * 模块说明：	
 * 修改历史：
 * 2018年3月15日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.service;

/**
 * 促销相关接口 <br>
 * 第三方只要实现该接口就可以使用促销组件。
 * 
 * @author Debenson
 * @since 0.1
 */
public interface PromotionService extends PromotionBrandService, PromotionCategoryService,
    PromotionCouponService, PromotionGoodsService, PromotionMemberService, PromotionOperatorService,
    PromotionOrderBillService, PromotionPrizeService, PromotionScoreService, PromotionStoreService,
    PromotionResultService {

}
