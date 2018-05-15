/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	Action.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月13日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.action;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.gomore.experiment.promotion.model.SerializationConstants;

/**
 * 促销动作。
 * 
 * @author Debenson
 * @since 0.1
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY,
    property = SerializationConstants.JSON_TYPE_FIELD)
@JsonSubTypes({
    @Type(value = DeductionAction.class, name = DeductionAction.CTYPE),
    @Type(value = DiscountAction.class, name = DiscountAction.CTYPE),
    @Type(value = GoodsAction.class, name = GoodsAction.CTYPE),
    @Type(value = NScoreAction.class, name = NScoreAction.CTYPE),
    @Type(value = ScoreAction.class, name = ScoreAction.CTYPE),
    @Type(value = CouponAction.class, name = CouponAction.CTYPE),
    @Type(value = PrizeAction.class, name = PrizeAction.CTYPE),
    @Type(value = UseCouponAction.class, name = UseCouponAction.CTYPE),
    @Type(value = GoodsDiscountAction.class, name = GoodsDiscountAction.CTYPE) })
public interface Action extends Serializable {

  /** 默认优先级 */
  public static final int DEF_PRIORITY = 1;

  /**
   * 条件类型
   * 
   * @return
   */
  @JsonIgnore
  public String getType();

  /**
   * 促销主体。
   * 
   * @return
   */
  public ActionBody getBody();

  /**
   * 促销描述。
   * 
   * @return
   */
  public String getDescription();

  /**
   * 设置促销描述
   * 
   * @param description
   */
  public void setDescription(String description);

  /**
   * 优先级，数值越大优先级越高。促销引擎会按照促销结果的优先级顺序从大到小返回。 一般来讲， 应用取得促销结果后，
   * 按照促销结果的顺序来执行促销。当然，应用也可以按照自己的顺序的执行促销，这个促销引擎本身并不关心。
   * 
   * <br>
   * 一个典型的例子是： 抵扣促销和折扣促销， 比如，存在这样的促销规则:
   * <ol>
   * <li>订单总金额大于等于90，则可以抵扣10元；</li>
   * <li>订单总金额大于等于100，则打8折；</li>
   * </ol>
   * 当前订单总金额为100， 则同时享受上述两个促销，但是执行顺序不一样，会导致最终计算的订单应付金额也会不一样：
   * <ul>
   * <li>如果先计算抵扣，则应付金额=(100-10) * 0.8 = 72元；</li>
   * <li>如果先计算折扣，则应付金额=100 * 0.8 - 10 = 70元；</li>
   * </ul>
   * 由此可见， 促销结果的优先级将直接影响最终促销结果。
   * 
   * @return 优先级，默认值： {@link #DEF_PRIORITY}。
   */
  public Integer getPriority();

  /**
   * 与之冲突的促销结果类型。 <br>
   * 业务上有时会设置促销结果是不能与另外一些促销同时享受，通过设置此值就可以达到此目的。 <br>
   * 注意：促销引擎只负责通知应用哪些促销结果相互冲突， 处理这些冲突交由应用自己决定。
   * 
   * @return 与之冲突的促销结果类型。
   */
  public List<String> getConflicts();

  /**
   * 取得产生这次促销结果的促销单号。
   * 
   * @return
   */
  public String getPromotionBillNumber();

  /**
   * 设置来源促销单号。
   * 
   * @param promotionBillNumber
   */
  public void setPromotionBillNumber(String promotionBillNumber);

  /**
   * 按指定的叠加计算促销结果。
   * 
   * @param step
   *          叠加的步长。
   * @return 促销结果集合。
   */
  public List<Action> stepActions(BigDecimal step);

}
