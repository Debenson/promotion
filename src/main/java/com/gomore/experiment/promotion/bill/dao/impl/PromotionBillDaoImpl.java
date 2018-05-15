/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	PromotionBillDaoImpl.java
 * 模块说明：	
 * 修改历史：
 * 2016年9月22日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.bill.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.gomore.experiment.commons.exception.ServiceException;
import com.gomore.experiment.commons.rest.PageResult;
import com.gomore.experiment.promotion.bean.QueryResultPaging;
import com.gomore.experiment.promotion.bill.bean.PromotionBill;
import com.gomore.experiment.promotion.bill.bean.PromotionBillFilter;
import com.gomore.experiment.promotion.bill.dao.PromotionBillDao;
import com.gomore.experiment.promotion.service.PromotionOperatorService;
import com.gomore.experiment.promotion.service.bean.Operator;
import com.mongodb.client.result.DeleteResult;

/**
 * @author Debenson
 * @since 0.1
 */
@Service
public class PromotionBillDaoImpl implements PromotionBillDao {

  @Autowired
  private MongoTemplate mongoTemplate;
  @Autowired
  private PromotionOperatorService operatorService;

  @Override
  public Long save(PromotionBill bill) throws ServiceException {
    Assert.notNull(bill, "bill");

    final Operator user = operatorService.getCurrentOperator();
    if (bill.getId() == null) {
      bill.setId(IdWorker.getId());
      bill.setCreateTime(new Date());
      if (user != null) {
        bill.setCreatorId(Long.valueOf(user.getUuid()));
        bill.setCreatorName(user.getName());
        bill.setOrgId(Long.valueOf(user.getOrgId()));
      }
    }
    bill.setUpdateTime(new Date());

    if (user != null) {
      bill.setUpdatorId(Long.valueOf(user.getUuid()));
      bill.setUpdatorName(user.getName());
    }
    this.mongoTemplate.save(bill);
    return bill.getId();
  }

  @Override
  public PromotionBill get(Long id) {
    Assert.notNull(id, "id");

    PromotionBill bill = mongoTemplate.findById(id, PromotionBill.class);
    return bill;
  }

  @Override
  public boolean remove(Long id) throws ServiceException {
    Assert.notNull(id, "id");

    Query query = new Query();
    query.addCriteria(new Criteria("id").is(id));
    List<PromotionBill> results = mongoTemplate.findAllAndRemove(query, PromotionBill.class);
    return results.size() > 0;
  }

  @Override
  public long removeAll() throws ServiceException {
    Query query = new Query();
    DeleteResult wr = mongoTemplate.remove(query, PromotionBill.class);
    return wr.getDeletedCount();
  }

  @Override
  public PageResult<PromotionBill> query(PromotionBillFilter filter) {
    Assert.notNull(filter, "condition");

    PageResult<PromotionBill> result = new PageResult<PromotionBill>();
    QueryResultPaging paging = new QueryResultPaging();
    paging.setPage(filter.getPage());
    paging.setPageSize(filter.getPageSize());

    // 查询总记录数
    Query queryObj = PromotionBillQueryBuilder.getInstance().build(filter);
    long allCount = mongoTemplate.count(queryObj, PromotionBill.class);
    paging.setRecordCount(allCount);
    if (paging.getPageSize() <= 0) {
      paging.setPageCount(1);
    } else {
      long m = 0;
      if (allCount % paging.getPageSize() != 0) {
        m = 1;
      }
      paging.setPageCount(Long.valueOf(allCount / paging.getPageSize() + m).intValue());
    }

    // 查询记录
    List<PromotionBill> records = mongoTemplate.find(queryObj, PromotionBill.class);
    result.setRecords(records);
    result.setPage(paging.getPage());
    result.setPageCount(paging.getPageCount());
    result.setPageSize(paging.getPageSize());
    result.setRecordCount((int) paging.getRecordCount());
    return result;
  }

  @Override
  public PromotionBill getByUniqueField(String fieldName, Object fieldValue) {
    Assert.notNull(fieldName, "fieldName");

    Query query = new Query();
    query.addCriteria(new Criteria(fieldName).is(fieldValue));
    return mongoTemplate.findOne(query, PromotionBill.class);
  }

  @Override
  public List<PromotionBill> getByFields(Object... fieldNameAndValues) {
    Assert.notNull(fieldNameAndValues, "fieldNameAndValues");
    if (fieldNameAndValues.length % 2 != 0) {
      throw new IllegalArgumentException("参数格式非法");
    }

    Query query = new Query();
    for (int i = 0; i < fieldNameAndValues.length - 1; ++i) {
      String fieldName = (String) fieldNameAndValues[i];
      Assert.notNull(fieldName, "fieldName");

      Object fieldValue = fieldNameAndValues[++i];
      query.addCriteria(new Criteria(fieldName).is(fieldValue));
    }
    return mongoTemplate.find(query, PromotionBill.class);
  }

}
