/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	PromotionBillDao.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月22日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.bill.dao;

import java.util.List;

import com.gomore.experiment.commons.exception.ServiceException;
import com.gomore.experiment.commons.rest.PageResult;
import com.gomore.experiment.promotion.bill.bean.PromotionBill;
import com.gomore.experiment.promotion.bill.bean.PromotionBillFilter;

/**
 * @author Debenson
 * @since 0.1
 */
public interface PromotionBillDao {

  /**
   * 保存促销单。
   * 
   * @param bill
   * @return 单据ID
   * @throws ServiceException
   */
  public Long save(PromotionBill bill) throws ServiceException;

  /**
   * 根据 {@link PromotionBill#getId()}取得促销单。
   * 
   * @param id
   *          促销单标识，禁止为null。
   * @return
   */
  public PromotionBill get(Long id);

  /**
   * 删除促销单。
   * 
   * @param id
   * @return
   * @throws ServiceException
   */
  public boolean remove(Long id) throws ServiceException;

  /**
   * 删除全部促销单。
   * 
   * @return
   * @throws ServiceException
   */
  public long removeAll() throws ServiceException;

  /**
   * 分页查询。
   * 
   * @param filter
   * @return
   */
  public PageResult<PromotionBill> query(PromotionBillFilter filter);

  /**
   * @param fieldName
   * @param fieldValue
   * @return
   */
  public PromotionBill getByUniqueField(String fieldName, Object fieldValue);

  /**
   * @param fieldNameAndValues
   * @return
   */
  public List<PromotionBill> getByFields(Object... fieldNameAndValues);

}
