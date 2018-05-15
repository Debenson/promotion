package com.gomore.experiment.promotion.common;

/**
 * 标识类可接受注入
 * 
 * @author Debenson
 * @since 0.1
 */
public interface Injectable {

  /**
   * 将指定源对象中的数据注入当前对象中。
   * 
   * @param source
   *          源对象，传入null将不做任何事。
   */
  public void inject(Object source);

}
