/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	PromotionBillServiceImpl.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月22日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.bill.service.impl;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.gomore.experiment.commons.exception.ServiceException;
import com.gomore.experiment.commons.rest.PageResult;
import com.gomore.experiment.promotion.bill.bean.PromotionBill;
import com.gomore.experiment.promotion.bill.bean.PromotionBillFilter;
import com.gomore.experiment.promotion.bill.bean.PromotionBillState;
import com.gomore.experiment.promotion.bill.dao.PromotionBillDao;
import com.gomore.experiment.promotion.bill.service.PromotionBillService;
import com.gomore.experiment.promotion.model.condition.BusinessCondition;
import com.gomore.experiment.promotion.model.condition.CompositeCondition;
import com.gomore.experiment.promotion.model.condition.Condition;
import com.gomore.experiment.promotion.model.condition.StoreCondition;
import com.gomore.experiment.promotion.model.condition.TimeRangeCondition;
import com.gomore.experiment.promotion.model.exp.IfThenExpression;
import com.gomore.experiment.promotion.service.PromotionOperatorService;
import com.gomore.experiment.promotion.service.bean.Operator;
import com.jarvis.cache.annotation.Cache;
import com.jarvis.cache.annotation.CacheDelete;
import com.jarvis.cache.annotation.CacheDeleteKey;

/**
 * 促销单维护服务。
 * 
 * @author Debenson
 * @since 0.1
 */
@Service("PromotionBillServiceImpl")
@Transactional
public class PromotionBillServiceImpl implements PromotionBillService {

  @Autowired
  private PromotionBillDao promotionBillDao;
  @Autowired
  private PromotionOperatorService operatorService;

  @Override
  @CacheDelete(@CacheDeleteKey(value = "queryPromotionBills"))
  public Long save(PromotionBill bill) throws ServiceException {
    return promotionBillDao.save(bill);
  }

  @Override
  public PromotionBill get(Long id) {
    return promotionBillDao.get(id);
  }

  @Override
  @CacheDelete(@CacheDeleteKey(value = "queryPromotionBills"))
  public boolean remove(Long id) throws ServiceException {
    Assert.notNull(id, "id");

    PromotionBill bill = get(id);
    if (bill == null) {
      throw new ServiceException("找不到指定的促销单");
    }
    if (!PromotionBillState.unsubmit.equals(bill.getState())) {
      throw new ServiceException("促销单不是未提交状态，不允许删除");
    }

    promotionBillDao.remove(id);
    return true;
  }

  @Override
  @Cache(key = "queryPromotionBills", hfield = "#hash(#args)", expire = 60 * 60 * 24)
  public PageResult<PromotionBill> query(PromotionBillFilter filter) {
    return promotionBillDao.query(filter);
  }

  @Override
  @CacheDelete(@CacheDeleteKey(value = "queryPromotionBills"))
  public boolean submit(Long id) throws ServiceException {
    Assert.notNull(id, "id");

    PromotionBill bill = get(id);
    if (bill == null) {
      throw new ServiceException("找不到指定的促销单");
    }

    if (PromotionBillState.submit.equals(bill.getState())) {
      return false;
    } else if (!PromotionBillState.unsubmit.equals(bill.getState())) {
      throw new ServiceException("促销单不是未提交状态，不允许提交");
    }

    bill.setState(PromotionBillState.submit);
    save(bill);
    return true;
  }

  @Override
  @CacheDelete(@CacheDeleteKey(value = "queryPromotionBills"))
  public boolean abolish(Long id) throws ServiceException {
    Assert.notNull(id, "id");

    PromotionBill bill = get(id);
    if (bill == null) {
      throw new ServiceException("找不到指定的促销单");
    }

    if (PromotionBillState.abolished.equals(bill.getState())) {
      return false;
    } else if (!PromotionBillState.submit.equals(bill.getState())) {
      throw new ServiceException("促销单不是已提交状态，不允许作废");
    }

    bill.setState(PromotionBillState.abolished);
    save(bill);
    return true;
  }

  @Override
  public PromotionBill createBill() {
    final Operator user = operatorService.getCurrentOperator();

    PromotionBill bill = new PromotionBill();
    bill.setState(PromotionBillState.unsubmit);
    bill.setCreateTime(new Date());
    bill.setUpdateTime(new Date());
    if (user != null) {
      bill.setCreatorId(Long.valueOf(user.getUuid()));
      bill.setCreatorName(user.getName());
      bill.setOrgId(Long.valueOf(user.getOrgId()));
      bill.setUpdatorId(Long.valueOf(user.getUuid()));
      bill.setUpdatorName(user.getName());
    }
    bill.setSubject("");
    bill.setRemark("");

    // 促销时间条件
    DateTime dt = new DateTime();
    DateTime dtBegin = dt.dayOfMonth().roundFloorCopy();
    DateTime dtEnd = dtBegin.plusMonths(1).dayOfMonth().roundFloorCopy().minusSeconds(1);
    TimeRangeCondition timeCondition = new TimeRangeCondition(dtBegin.toDate(), dtEnd.toDate());

    // 门店或业态条件
    Condition storeOrBizCondition = CompositeCondition.or(
        new StoreCondition(StoreCondition.ANY_STORE),
        new BusinessCondition(BusinessCondition.ANY_BIZ));
    Condition precondition = CompositeCondition.and(timeCondition, storeOrBizCondition);
    bill.setPrecondition(precondition); // 设置前置条件

    // 默认为组合条件。
    IfThenExpression exp = new IfThenExpression();
    exp.setCondition(CompositeCondition.and());
    bill.addExp(exp);
    return bill;
  }

}
