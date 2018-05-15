/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	UCN.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月13日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.common;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;

import io.swagger.annotations.ApiModel;

/**
 * UUID + CODE + NAME
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
@ApiModel(description = "UUID + CODE + NAME")
public class UCN implements HasUCN, Injectable, Serializable {
  private static final long serialVersionUID = -7440693311119211378L;

  private String uuid;
  private String code;
  private String name;

  /**
   * 构造对象。
   */
  public UCN() {
    // Do Nothing
  }

  /**
   * 指定全局唯一标识、代码和名称构造对象。
   * 
   * @param uuid
   * @param code
   * @param name
   */
  public UCN(String uuid, String code, String name) {
    this.uuid = uuid;
    this.code = code;
    this.name = name;
  }

  /**
   * 复制 {@link HasUCN}到新对象。
   * 
   * @param source
   * @return
   */
  public static UCN newInstance(HasUCN source) {
    UCN ucn = new UCN();
    ucn.inject(source);
    return ucn;
  }

  @Override
  public String getUuid() {
    return uuid;
  }

  @Override
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  @Override
  public String getCode() {
    return code;
  }

  @Override
  public void setCode(String code) {
    this.code = code;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void inject(Object source) {
    if (source instanceof HasUCN) {
      HasUCN other = (HasUCN) source;
      this.uuid = other.getUuid();
      this.code = other.getCode();
      this.name = other.getName();
    }
  }

  @Override
  public UCN clone() {
    UCN copy = new UCN();
    copy.inject(this);
    return copy;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((code == null) ? 0 : code.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof UCN)) {
      return false;
    }
    UCN other = (UCN) obj;
    return Objects.equal(getUuid(), other.getUuid()) && Objects.equal(getCode(), other.getCode())
        && Objects.equal(getName(), other.getName());
  }

  @Override
  public String toString() {
    return "UCN [uuid=" + uuid + ", code=" + code + ", name=" + name + "]";
  }

  /**
   * 返回友好的字符串
   * 
   * @return
   */
  public String toFriendlyString() {
    return name + "[" + code + "]";
  }

}
