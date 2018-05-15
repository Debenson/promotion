/**
 * 版权所有(C)，上海勾芒信息科技，2017，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	PromotionResult.java
 * 模块说明：	
 * 修改历史：
 * 2017年3月14日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine;

import java.io.Serializable;
import java.math.BigDecimal;

import com.gomore.experiment.promotion.model.condition.GoodsRange;
import com.gomore.experiment.promotion.model.condition.Logical;

import lombok.Data;

/**
 * 单个促销条件的计算结果。
 * 
 * @author Debenson
 * @since 0.1
 */
@Data
public class ConditionResult implements Serializable {
  private static final long serialVersionUID = 285130086498815076L;

  public static final BigDecimal NOT_STEP = BigDecimal.ZERO;

  /** 是否接受促销 */
  private boolean accept;
  /** 促销的叠加数，仅当接受促销时有效，为null表示不支持叠加。 */
  private BigDecimal step = NOT_STEP;
  /**
   * 生效的商品范围
   */
  private GoodsRange goodsRange;

  /**
   * 不接受促销条件。
   * 
   * @return
   */
  public static ConditionResult refuse() {
    return accept(false);
  }

  /**
   * 接受单次促销条件。
   * 
   * @param accept
   * @return
   */
  public static ConditionResult accept(boolean accept) {
    return new ConditionResult(accept, NOT_STEP);
  }

  /**
   * 接受多次叠加。
   * 
   * @param step
   * @return
   */
  public static ConditionResult acceptStep(BigDecimal step) {
    return new ConditionResult(true, step);
  }

  /**
   * 
   */
  public ConditionResult() {
    this(false, NOT_STEP);
  }

  /**
   * @param accept
   * @param step
   */
  public ConditionResult(boolean accept, BigDecimal step) {
    this.accept = accept;
    this.step = step == null ? NOT_STEP : step;
    if (step != null && step.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("叠加不能0");
    }
  }

  /**
   * 合并计算结果。 <br>
   * 
   * @param a
   * @param b
   * @param logical
   * @return
   */
  public static ConditionResult merge(ConditionResult a, ConditionResult b, Logical logical) {
    ConditionResult cr = null;
    if (Logical.and.equals(logical)) {
      cr = and(a, b);
    } else if (Logical.or.equals(logical)) {
      cr = or(a, b);
    } else {
      cr = ConditionResult.refuse();
    }
    if (cr.isAccept()) {
      // 合并商品范围
      cr.setGoodsRange(GoodsRange.merge(a.getGoodsRange(), b.getGoodsRange()));
    }
    return cr;
  }

  /**
   * <table border="1">
   * <tr>
   * <td colspan=2>叠加</td>
   * <td>运算表达式</td>
   * <td>结果</td>
   * </tr>
   * 
   * <tr>
   * <td>Y</td>
   * <td>Y</td>
   * <td>(T,2) and (T,3)</td>
   * <td>(T,2)
   * </tr>
   * 
   * <tr>
   * <td>Y</td>
   * <td>N</td>
   * <td>(T,2) and (T,0)</td>
   * <td>(T,2)
   * </tr>
   * 
   * <tr>
   * <td>N</td>
   * <td>Y</td>
   * <td>(T,0) and (T,3)</td>
   * <td>(T,3)
   * </tr>
   * 
   * <tr>
   * <td>N</td>
   * <td>N</td>
   * <td>(T,0) and (T,N)</td>
   * <td>(T,0)
   * </tr>
   * 
   * </table>
   * 
   * @param a
   * @param b
   * @return
   */
  private static ConditionResult and(ConditionResult a, ConditionResult b) {
    boolean accept = a.isAccept() && b.isAccept();
    if (!accept) {
      return ConditionResult.refuse();
    }

    BigDecimal step = NOT_STEP;
    if (a.supportsStep() && b.supportsStep()) {
      step = min(a.getStep(), b.getStep());
    } else if (a.supportsStep()) {
      step = a.getStep();
    } else if (b.supportsStep()) {
      step = b.getStep();
    }
    return ConditionResult.acceptStep(step);
  }

  /**
   * <table border=1>
   * <tr>
   * <td colspan=2>叠加</td>
   * <td>运算表达式</td>
   * <td>结果</td>
   * </tr>
   * 
   * <tr>
   * <td>Y</td>
   * <td>Y</td>
   * <td>(T,2) or (T,3)</td>
   * <td>(T,5)
   * </tr>
   * 
   * <tr>
   * <td>Y</td>
   * <td>N</td>
   * <td>(T,2) or (T,0)</td>
   * <td>(T,3)
   * </tr>
   * 
   * <tr>
   * <td>N</td>
   * <td>Y</td>
   * <td>(T,0) or (T,3)</td>
   * <td>(T,4)
   * </tr>
   * 
   * <tr>
   * <td>N</td>
   * <td>N</td>
   * <td>(T,0) or (T,0)</td>
   * <td>(T,0)
   * </tr>
   * 
   * </table>
   * 
   * @param a
   * @param b
   * @return
   */
  private static ConditionResult or(ConditionResult a, ConditionResult b) {
    boolean accept = a.isAccept() || b.isAccept();
    if (!accept) {
      // 两个条件都不成立
      return ConditionResult.refuse();
    }

    BigDecimal step = NOT_STEP;
    if (a.isAccept() && b.isAccept()) {
      // 两个条件都 成立
      if (a.supportsStep() && b.supportsStep()) {
        // 两个条件都支持叠加
        step = a.getStep().add(b.getStep());
      } else if (a.supportsStep()) {
        // 如果其中一个支持叠加，返回叠加+1
        step = a.getStep().add(BigDecimal.ONE);
      } else if (b.supportsStep()) {
        // 如果其中一个支持叠加，返回叠加+1
        step = b.getStep().add(BigDecimal.ONE);
      }

    } else if (a.isAccept()) {
      // a 成立
      return a;

    } else if (b.isAccept()) {
      // b 成立
      return b;
    }

    return new ConditionResult(accept, step);
  }

  private static BigDecimal min(BigDecimal a, BigDecimal b) {
    double d = Math.min(a.doubleValue(), b.doubleValue());
    return BigDecimal.valueOf(d);
  }

  /**
   * @param other
   * @param logical
   * @return
   */
  public ConditionResult merge(ConditionResult other, Logical logical) {
    return ConditionResult.merge(this, other, logical);
  }

  /**
   * 是否支持叠加。
   * 
   * @return
   */
  public boolean supportsStep() {
    return getStep().intValue() != NOT_STEP.intValue();
  }

}
