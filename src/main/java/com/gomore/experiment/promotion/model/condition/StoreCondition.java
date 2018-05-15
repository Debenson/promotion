/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	StoreCondition.java
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
 * 店铺范围促销条件。
 * 
 * <br>
 * 指交易的店铺在指定的店铺中。一般而言， 如果没有设置，就默认为全部门店。
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public class StoreCondition extends AbstractCondition {
  private static final long serialVersionUID = 5886055661572562526L;

  public static final String CTYPE = "storeCondition";

  /** 表示全部门店 */
  public static final UCN ANY_STORE = new UCN("ALL", "", "全部");

  private List<UCN> stores = Lists.newArrayList();

  /**
   * 包含全部门店
   */
  public StoreCondition() {
  }

  /**
   * 指定门店
   * 
   * @param stores
   */
  public StoreCondition(UCN... stores) {
    if (stores != null) {
      this.stores.addAll(Lists.newArrayList(stores));
    }
  }

  @Override
  public String getType() {
    return CTYPE;
  }

  /**
   * 门店列表。
   * 
   * @return
   */
  public List<UCN> getStores() {
    return stores;
  }

  public void setStores(List<UCN> stores) {
    this.stores = stores;
  }

  /**
   * 添加门店。
   * 
   * @param store
   */
  public void addStore(UCN store) {
    if (store != null && !has(store)) {
      this.stores.add(store);
    }
  }

  /**
   * 门店是否已经存在
   * 
   * @param store
   * @return
   */
  public boolean has(UCN store) {
    for (UCN ucn : getStores()) {
      if (Objects.equals(ucn.getUuid(), store.getUuid())) {
        return true;
      }
    }
    return false;
  }

  /**
   * 是否允许全部门店。
   *
   * @return
   */
  @JsonIgnore
  public boolean acceptAny() {
    for (UCN store : stores) {
      if (ANY_STORE.getCode().equals(store.getCode())) {
        return true;
      }
    }
    return false;

  }

}
