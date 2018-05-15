/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	PromotionBillService.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月22日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.bill.service;

import com.gomore.experiment.commons.exception.ServiceException;
import com.gomore.experiment.commons.rest.PageResult;
import com.gomore.experiment.promotion.bill.bean.PromotionBill;
import com.gomore.experiment.promotion.bill.bean.PromotionBillFilter;

/**
 * 促销单服务。
 * 
 * @author Debenson
 * @since 0.1
 */
public interface PromotionBillService {

  /**
   * 创建默认的空促销单，单据未保存
   * 
   * @return
   */
  public PromotionBill createBill();

  /**
   * 保存促销单。
   * 
   * <br>
   * 新增保存时， {@link PromotionBill#getId()}和{@link PromotionBill#getBillNumber()}
   * 都由系统自动生成。
   * 
   * @param bill
   *          促销单， 禁止为null。
   * @return 返回促销单标识。
   * @throws ServiceException
   *           操作失败抛出此异常。
   */
  public Long save(PromotionBill bill) throws ServiceException;

  /**
   * 通过标识查找促销单。
   * 
   * @param id
   *          促销单标识，禁止为null。
   * @return 返回促销单， 找不到返回null。
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
   * 提交促销单。
   * 
   * @param id
   *          单据标识，禁止为null。
   * @return 提交是否成功。
   * @throws ServiceException
   */
  public boolean submit(Long id) throws ServiceException;

  /**
   * 作废促销单
   * 
   * @param id
   *          单据标识，禁止为null。
   * @return 作废是否成功。
   * @throws ServiceException
   */
  public boolean abolish(Long id) throws ServiceException;

  /**
   * 查询促销单。
   * 
   * @param filter
   *          查询条件。
   * @return 查询结果集合。
   */
  public PageResult<PromotionBill> query(PromotionBillFilter filter);

}
