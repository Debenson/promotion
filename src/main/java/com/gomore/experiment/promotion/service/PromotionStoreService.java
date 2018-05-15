/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	StoreService.java
 * 模块说明：	
 * 修改历史：
 * 2016年11月7日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.service;

import java.util.List;

import com.gomore.experiment.promotion.common.HasUCN;
import com.gomore.experiment.promotion.common.UCN;

/**
 * 门店服务。
 * 
 * @author Debenson
 * @since 0.1
 */
public interface PromotionStoreService {

  /**
   * 判断lower是否为upper的下级门店。
   * 
   * @param lower
   *          下级门店。
   * @param upper
   *          上级门店。
   * @return 特别地，如果lower.equlas(upper)也返回true。
   */
  public boolean storeBelongTo(HasUCN lower, HasUCN upper);

  /**
   * 通过标识取得门店。
   * 
   * @param id
   *          门店标识，禁止为null。
   * @return 找不到返回null。
   */
  public UCN getStore(String id);

  /**
   * 查询下级门店列表
   * 
   * @param pid
   *          上次门店标识，如果=null表示查询所有一级门店。
   * @return 门店列表
   */
  public List<UCN> getStores(String pid);

}
