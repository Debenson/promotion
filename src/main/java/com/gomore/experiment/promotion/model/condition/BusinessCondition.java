/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	BusinessCondition.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月13日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.condition;

import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gomore.experiment.promotion.common.UCN;
import com.google.common.collect.Lists;

/**
 * 业态范围促销条件。
 * 
 * <br>
 * 指交易的业态在指定的业态中。一般而言， 如果没有设置，就默认为全部业态。
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public class BusinessCondition extends AbstractCondition {
  private static final long serialVersionUID = -4919463882479188477L;

  public static final String CTYPE = "businessCondition";

  /** 表示全部业态 */
  public static final UCN ANY_BIZ = new UCN("ALL", "", "全部");

  private List<UCN> business = Lists.newArrayList();

  /**
   * 包含全部业态
   */
  public BusinessCondition() {
  }

  /**
   * 指定业态
   * 
   * @param stores
   */
  public BusinessCondition(UCN... stores) {
    if (stores != null) {
      this.business.addAll(Lists.newArrayList(stores));
    }
  }

  @Override
  public String getType() {
    return CTYPE;
  }

  /**
   * 业态列表。
   * 
   * @return
   */
  public List<UCN> getBusiness() {
    return business;
  }

  public void setBusiness(List<UCN> business) {
    this.business = business;
  }

  /**
   * 添加业态。
   * 
   * @param business
   */
  public void addBusiness(UCN business) {
    if (business != null && !has(business)) {
      this.business.add(business);
    }
  }

  /**
   * 业态是否已经存在
   * 
   * @param business
   * @return
   */
  public boolean has(UCN business) {
    for (UCN ucn : getBusiness()) {
      if (Objects.equals(ucn.getUuid(), business.getUuid())) {
        return true;
      }
    }
    return false;
  }

  /**
   * 是否允许全部业态。
   *
   * @return
   */
  @JsonIgnore
  public boolean acceptAny() {
    for (UCN store : business) {
      if (ANY_BIZ.getCode().equals(store.getCode())) {
        return true;
      }
    }
    return false;

  }

}
