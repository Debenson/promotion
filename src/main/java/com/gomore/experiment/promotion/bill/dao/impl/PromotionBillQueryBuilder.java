/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	PromotionBillQueryBuilder.java
 * 模块说明：	
 * 修改历史：
 * 2016年10月2日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.bill.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;

import com.gomore.experiment.promotion.bill.bean.PromotionBillFilter;

/**
 * @author Debenson
 * @since 0.1
 */
public class PromotionBillQueryBuilder {

  private static PromotionBillQueryBuilder instance;

  private PromotionBillQueryBuilder() {
  }

  public static PromotionBillQueryBuilder getInstance() {
    if (instance == null) {
      instance = new PromotionBillQueryBuilder();
    }
    return instance;
  }

  public Query build(PromotionBillFilter condition) {
    Assert.notNull(condition, "condition");

    Query query = new Query();
    buildQuery(query, condition);
    buildPaging(query, condition);
    return query;
  }

  private void buildPaging(Query query, PromotionBillFilter condition) {
    List<Order> orders = new ArrayList<>();

    if (StringUtils.isNotBlank(condition.getOrderField())) {
      Direction dir = condition == null ? Direction.ASC
          : Direction.valueOf(condition.getOrderDirection().name().toUpperCase());
      orders.add(new Order(dir, condition.getOrderField()));
    }

    // 如果每页记录数<=0，则表示取全部数据
    int actualPageSize = condition.getPageSize();
    if (actualPageSize <= 0) {
      actualPageSize = Integer.MAX_VALUE;
    }
    PageRequest pageable = null;
    if (orders.isEmpty()) {
      pageable = PageRequest.of(condition.getPage() - 1, actualPageSize);
    } else {
      pageable = PageRequest.of(condition.getPage() - 1, actualPageSize, Sort.by(orders));
    }
    query.with(pageable);
  }

  private void buildQuery(Query query, PromotionBillFilter condition) {
    if (condition.getOrgIdEquals() != null) {
      query.addCriteria(new Criteria("orgId").is(condition.getOrgIdEquals()));
    }

    if (condition.getIdEquals() != null) {
      query.addCriteria(new Criteria("id").is(condition.getIdEquals()));
    }

    if (condition.getStateIn() != null && condition.getStateIn().length > 0) {
      Object[] states = condition.getStateIn();
      query.addCriteria(new Criteria("state").in(states));
    }

    if (StringUtils.isNotBlank(condition.getSubjectLike())) {
      query.addCriteria(new Criteria("subject").regex(condition.getSubjectLike()));
    }

    if (condition.getEffectived() != null) {
      Date now = new Date();
      if (condition.getEffectived().booleanValue()) {
        query.addCriteria(new Criteria("precondition.getConditions[0].begin").lte(now)
            .and("precondition.getConditions[0].end").gte(now));
      } else {
        query.addCriteria(new Criteria("precondition.getConditions[0].begin").gt(now)
            .orOperator(new Criteria("precondition.getConditions[0].end").lt(now)));
      }
    }

    if (condition.getExpiredOnly() != null) {
      query.addCriteria(new Criteria("precondition.getConditions[0].end").lt(new Date()));
    }

  }

}
