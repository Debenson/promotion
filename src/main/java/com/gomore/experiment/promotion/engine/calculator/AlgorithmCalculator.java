/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	AlgorithmCalculator.java
 * 模块说明：	
 * 修改历史：
 * 2016年10月15日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine.calculator;

import org.springframework.util.Assert;

import com.gomore.experiment.promotion.model.condition.Comparisons;

/**
 * @author Debenson
 * @since 0.1
 */
public class AlgorithmCalculator {

  /**
   * 比较运算
   * 
   * @param a
   * @param b
   * @param operator
   * @return
   */
  public static <T> boolean compare(Comparable<T> a, T b, Comparisons operator) {
    if (Comparisons.eq.equals(operator)) {
      return a.compareTo(b) == 0;
    } else if (Comparisons.gt.equals(operator)) {
      return a.compareTo(b) > 0;
    } else if (Comparisons.gte.equals(operator)) {
      return a.compareTo(b) >= 0;
    } else if (Comparisons.lt.equals(operator)) {
      return a.compareTo(b) < 0;
    } else if (Comparisons.lte.equals(operator)) {
      return a.compareTo(b) <= 0;
    } else if (Comparisons.ne.equals(operator)) {
      return a.compareTo(b) != 0;
    } else {
      return false;
    }
  }

  /**
   * 判断指定的值 {@code the} 是否介于 {@code from}和{@code to}之间。
   * 
   * @param the
   * @param from
   *          起始值，非空
   * @param to
   *          截止值，可空。空表示无穷大。
   * @return
   */
  public static <T> boolean isBetween(Comparable<T> the, T from, T to) {
    Assert.notNull(the, "the");
    Assert.notNull(from, "from");

    boolean b = compare(the, from, Comparisons.gte);
    if (!b) {
      return false;
    }
    if (to != null) {
      b = compare(the, to, Comparisons.lte);
    }
    return b;
  }

}
