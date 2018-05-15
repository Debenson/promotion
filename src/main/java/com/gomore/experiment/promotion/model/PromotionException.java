/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	PromotionException.java
 * 模块说明：	
 * 修改历史：
 * 2016年11月2日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model;

import java.text.MessageFormat;

/**
 * 促销异常。
 * 
 * @author Debenson
 * @since 0.1
 */
public class PromotionException extends RuntimeException {
  private static final long serialVersionUID = 7270392660185575880L;

  public PromotionException() {
    super();
  }

  public PromotionException(String message) {
    super(message);
  }

  public PromotionException(String message, Throwable cause) {
    super(message, cause);
  }

  public PromotionException(Throwable cause) {
    super(cause);
  }

  public PromotionException(String pattern, Object... arguments) {
    this(MessageFormat.format(pattern, arguments));
  }

}
