/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	promotionBillController.java
 * 模块说明：	
 * 修改历史：
 * 2016年10月8日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.bill.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.gomore.experiment.commons.rest.JsonResponse;
import com.gomore.experiment.commons.rest.PageResult;
import com.gomore.experiment.promotion.bill.bean.PromotionBill;
import com.gomore.experiment.promotion.bill.bean.PromotionBillFilter;
import com.gomore.experiment.promotion.common.HasUCN;
import com.gomore.experiment.promotion.common.UCN;
import com.gomore.experiment.promotion.service.bean.Brand;
import com.gomore.experiment.promotion.service.bean.BrandFilter;
import com.gomore.experiment.promotion.service.bean.CategoryFilter;
import com.gomore.experiment.promotion.service.bean.Goods;
import com.gomore.experiment.promotion.service.bean.GoodsFilter;
import com.gomore.experiment.promotion.service.bean.UCNPageFilter;

import io.swagger.annotations.ApiParam;

/**
 * 促销单管理
 * 
 * @author Debenson
 * @since 0.1
 */
public interface IPromotionBillController {

  /**
   * 新增默认单据。
   *
   * @return
   */
  public JsonResponse<PromotionBill> createBill();

  /**
   * 根据Id获取单据。
   * 
   * @param id
   *          促销单标识
   * @return
   */
  public JsonResponse<PromotionBill> get(
      @RequestParam("id") @ApiParam(value = "促销单标识", required = true) Long id);

  /**
   * 保存单据。
   *
   * @param bill
   * @return
   */
  public JsonResponse<Long> saveBill(
      @RequestParam(value = "doSubmit", required = false) @ApiParam(value = "是否同时提交订单",
          required = false) Boolean doSubmit,
      @Validated @RequestBody @ApiParam(value = "订单信息", required = true) PromotionBill bill)
      throws Exception;

  /**
   * 删除促销单
   *
   * @param id
   *          促销单标识
   * @return
   * @throws Exception
   */
  public JsonResponse<Boolean> remove(
      @RequestParam("id") @ApiParam(value = "促销单标识", required = true) Long id) throws Exception;

  /**
   * 提交促销单
   *
   * @param id
   *          促销单标识
   * @return
   * @throws Exception
   */
  public JsonResponse<Void> submit(@RequestParam(value = "id",
      required = false) @ApiParam(value = "促销单标识", required = false) Long id) throws Exception;

  /**
   * 作废促销单
   *
   * @param id
   *          促销单标识
   * @return
   * @throws Exception
   */
  public JsonResponse<Void> abolish(
      @RequestParam("id") @ApiParam(value = "促销单标识", required = true) Long id);

  /**
   * 分页查询促销单
   *
   * @param filter
   *          筛选条件，非空
   * @return
   */
  public JsonResponse<PageResult<PromotionBill>> query(
      @NotNull @Validated @ApiParam(value = "查询条件", required = true) PromotionBillFilter filter);

  /**
   * 分页查询商品列表
   * 
   * @param filter
   *          筛选条件，非空
   * @return
   */
  public JsonResponse<PageResult<Goods>> queryGoods(@Validated @RequestParam(value = "filter",
      required = true) @ApiParam(value = "商品筛选条件", required = true) GoodsFilter filter);

  /**
   * 分页查询商品分类列表
   * 
   * @param filter
   *          筛选条件，非空
   * @return
   */
  public JsonResponse<PageResult<UCN>> queryCategory(@Validated @RequestParam(value = "filter",
      required = true) @ApiParam(value = "商品分类筛选条件", required = true) CategoryFilter filter);

  /**
   * 分页查询商品品牌列表
   * 
   * @param filter
   *          筛选条件，非空
   * @return
   */
  public JsonResponse<PageResult<Brand>> queryBrand(@Validated @RequestParam(value = "filter",
      required = true) @ApiParam(value = "商品品牌筛选条件", required = true) BrandFilter filter);

  /**
   * 分页查询券活动列表
   * 
   * @param filter
   *          筛选条件，非空
   * @return
   */
  public JsonResponse<PageResult<UCN>> queryCouponActivity(
      @Validated @RequestParam(value = "filter", required = true) @ApiParam(value = "券活动筛选条件",
          required = true) UCNPageFilter filter);

  /**
   * 取得门店列表
   *
   * @param pid
   *          上级门店id
   * @return
   */
  public JsonResponse<List<HasUCN>> getStores(@RequestParam(value = "pid",
      required = false) @ApiParam(value = "上级门店id", required = false) String pid);

}
