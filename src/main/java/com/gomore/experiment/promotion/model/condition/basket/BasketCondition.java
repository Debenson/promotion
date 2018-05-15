/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	BasketCondition.java
 * 模块说明：	
 * 修改历史：
 * 2016年10月23日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.condition.basket;

import javax.xml.bind.annotation.XmlRootElement;

import com.gomore.experiment.promotion.model.condition.AmountCondition;
import com.gomore.experiment.promotion.model.condition.CompositeCondition;
import com.gomore.experiment.promotion.model.condition.Condition;
import com.gomore.experiment.promotion.model.condition.CountCondition;
import com.gomore.experiment.promotion.model.condition.Logical;
import com.gomore.experiment.promotion.model.condition.goods.BrandCondition;
import com.gomore.experiment.promotion.model.condition.goods.CategoryCondition;
import com.gomore.experiment.promotion.model.condition.goods.GoodsCondition;

/**
 * 购物篮促销条件。
 * 
 * <br>
 * 购物篮促销条件， 是指针对指定的一组商品、分类或品牌， 计算数量或金额是否满足条件。结构上固定为两个 组合条件，第一个是商品范围条件，
 * 第二个是统计计算条件。<br>
 * 典型的应用场景：
 * 
 * <pre>
 * 欧普大转盘抽奖条件： 
 * 1、订单中 01大类（吸顶灯类）商品满足合计金额>=1199，并且该大类商品合计数量>=3 
 * 2、订单中 02大类（装饰灯类）商品 满足合计金额>=2999，并且该大类商品合计数量>=3
 * </pre>
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public class BasketCondition extends CompositeCondition<Condition> {
  private static final long serialVersionUID = 4629979165856822168L;

  public static final String CTYPE = "basketCondition";

  public BasketCondition() {
    super();
  }

  @Override
  public String getType() {
    return CTYPE;
  }

  /**
   * @param goodsCondition
   *          商品条件。
   * @param statisticsCondition
   *          统计条件。
   */
  public BasketCondition(Condition goodsCondition, Condition statisticsCondition) {
    super();
    if (!(goodsCondition instanceof CompositeCondition)) {
      goodsCondition = CompositeCondition.and(goodsCondition);
    }
    if (!(statisticsCondition instanceof CompositeCondition)) {
      statisticsCondition = CompositeCondition.and(statisticsCondition);
    }
    addCondition(goodsCondition, statisticsCondition);
  }

  /*
   * 固定为“与”条件。
   * 
   * @see com.gomore.experiment.promotion.model.condition.CompositeCondition#
   * getLogical()
   */
  @Override
  public Logical getLogical() {
    return Logical.and;
  }

  /**
   * 商品范围条件，只接受 {@link GoodsCondition}， {@link CategoryCondition}和
   * {@link BrandCondition}或其组合。
   * 
   */
  @SuppressWarnings("unchecked")
  public CompositeCondition<Condition> goodsCondition() {
    checkData();
    Condition condition = getConditions().get(0);
    return (CompositeCondition<Condition>) condition;
  }

  /**
   * 统计计算条件，目前接受: {@link CountCondition}, {@link AmountCondition}。
   */
  @SuppressWarnings("unchecked")
  public CompositeCondition<Condition> statisticsCondition() {
    checkData();
    Condition condition = getConditions().get(1);
    return (CompositeCondition<Condition>) condition;
  }

  /**
   * 检查数据合法性
   */
  private void checkData() {
    if (getConditions().size() != 2) {
      throw new IllegalArgumentException("购物篮促销条件数据非法");
    }
    for (Condition cond : getConditions()) {
      if (!(cond instanceof CompositeCondition)) {
        throw new IllegalArgumentException("购物篮促销条件数据非法");
      }
    }
  }

}
