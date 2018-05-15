/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	HasUCN.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月13日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.common;

/**
 * @author Debenson
 * @since 0.1
 */
public interface HasUCN {

  /**
   * 全局唯一标识。
   */
  String getUuid();

  /**
   * @see #getUuid()
   */
  void setUuid(String uuid);

  /**
   * 返回对象代码。
   * 
   * @return
   */
  public String getCode();

  /**
   * 设置对象代码。
   * 
   * @param code
   *          对象代码
   */
  public void setCode(String code);

  /**
   * 返回对象名称。
   * 
   * @return
   */
  public String getName();

  /**
   * 设置对象名称。
   * 
   * @param name
   *          对象名称
   */
  public void setName(String name);

}
