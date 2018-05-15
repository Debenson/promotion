/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	CommissionPromotionEngine.java
 * 模块说明：	
 * 修改历史：
 * 2016年8月9日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JsonMapperConfigurator;
import com.gomore.experiment.commons.rest.PageResult;
import com.gomore.experiment.commons.rest.QueryOrderDirection;
import com.gomore.experiment.promotion.bean.BeanSerializerListener;
import com.gomore.experiment.promotion.bean.MyBeanSerializerFactory;
import com.gomore.experiment.promotion.bill.bean.PromotionBill;
import com.gomore.experiment.promotion.bill.bean.PromotionBillFilter;
import com.gomore.experiment.promotion.bill.bean.PromotionBillState;
import com.gomore.experiment.promotion.bill.service.PromotionBillService;
import com.gomore.experiment.promotion.engine.Caculator;
import com.gomore.experiment.promotion.engine.CaculatorRegistry;
import com.gomore.experiment.promotion.engine.ConditionResult;
import com.gomore.experiment.promotion.engine.PromotionEngine;
import com.gomore.experiment.promotion.engine.PromotionResult;
import com.gomore.experiment.promotion.model.Context;
import com.gomore.experiment.promotion.model.PromotionException;
import com.gomore.experiment.promotion.model.action.Action;
import com.gomore.experiment.promotion.model.action.CouponAction;
import com.gomore.experiment.promotion.model.action.GoodsAction;
import com.gomore.experiment.promotion.model.action.PrizeAction;
import com.gomore.experiment.promotion.model.action.UseCouponAction;
import com.gomore.experiment.promotion.model.condition.Condition;
import com.gomore.experiment.promotion.model.exp.CaseExpression;
import com.gomore.experiment.promotion.model.exp.Expression;
import com.gomore.experiment.promotion.model.exp.IfThenExpression;
import com.gomore.experiment.promotion.service.bean.OrderBill;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 默认促销引擎实现。
 * 
 * <br>
 * 需要传入订单, 参考{@link Context#getOrder()}。
 * 
 * @author Debenson
 * @since 0.1
 */
@Component
public class DefaultPromotionEngine implements PromotionEngine, InitializingBean {
  private static final Logger logger = LoggerFactory.getLogger(DefaultPromotionEngine.class);

  private ObjectMapper mapper;
  private ThreadLocal<Context> tl = new ThreadLocal<>();

  @Autowired
  private CaculatorRegistry registry;
  @Autowired
  private PromotionBillService billService;

  @Override
  public ConditionResult accept(Condition condition, Context context) {
    Assert.notNull(context, "context");

    // 如果为null表示无条件接受促销
    if (condition == null) {
      ConditionResult r = ConditionResult.accept(true);
      context.addTrace(condition, r);
      return r;
    }

    // 根据条件计算
    ConditionResult r = ConditionResult.accept(false); // 默认不接受促销条件
    for (Caculator<Condition> caculator : registry.getCaculators()) {
      // 先判断是否支持当前条件，简化计算
      if (!caculator.support(condition)) {
        continue;
      }

      r = caculator.accept(condition, context);
      if (r.isAccept()) {
        // 只要任意一个计算器接受促销，就认为促销条件成立。
        break;
      }
    }

    // 记录表达式计算结果，用来跟踪计算路径
    context.addTrace(condition, r);
    return r;
  }

  @Override
  public PromotionResult execute(OrderBill bill, PromotionBill... promBills)
      throws PromotionException {
    Context context = new Context();
    context.put(Context.KEY_ORDER, bill);
    return execute(context, promBills);
  }

  @Override
  public PromotionResult execute(Context context, PromotionBill... promBills)
      throws PromotionException {
    if (promBills == null || promBills.length <= 0) {
      promBills = getAllBills(context).toArray(new PromotionBill[0]);
    }

    final StopWatch sw = new StopWatch();

    List<Action> allActions = Lists.newArrayList();
    List<PromotionBill> effectiveBills = Lists.newArrayList();
    sw.start("计算促销");
    for (PromotionBill promBill : promBills) {
      // 先计算前置条件
      final Condition preCondition = promBill.getPrecondition();
      ConditionResult r = accept(preCondition, context);
      if (!r.isAccept()) {
        // 前置条件不考虑叠加，如果不满足直接跳过。
        printCalcResult(promBill, context);
        continue;
      }

      // 计算促销表达式
      for (Expression exp : promBill.getExps()) {
        ExpCalcResult er = calcExp(exp, context);
        if (er.getResult().isAccept()) {

          // 设置来源促销单号，用于跟踪计算结果
          for (Action action : er.getActions()) {
            action.setPromotionBillNumber(promBill.getId().toString());
            action.setDescription(promBill.getSubject());
          }

          // 搜集促销结果
          allActions.addAll(er.getActions());

          // 记录生效的促销单
          if (!effectiveBills.contains(promBill)) {
            effectiveBills.add(promBill);
          }
        }
      }

      // 打印促销单计算结果
      printCalcResult(promBill, context);
    }
    sw.stop();

    sw.start("解决促销冲突");
    allActions = resolveConflicts(allActions);
    sw.stop();
    logger.debug(sw.prettyPrint());

    return new PromotionResultBuilder().actions(allActions).context(context)
        .effectiveBills(effectiveBills).build();
  }

  @Override
  public boolean containsAction(OrderBill bill, Action action, PromotionBill... promBills)
      throws PromotionException {
    Context context = new Context();
    context.put(Context.KEY_ORDER, bill);
    return containsAction(context, action, promBills);
  }

  @Override
  public boolean containsAction(Context context, Action action, PromotionBill... promBills)
      throws PromotionException {
    PromotionResult result = execute(context, promBills);
    return containAction(result.getActions(), action);
  }

  /**
   * 是否包含指定的促销结果
   * 
   * @param actions
   * @param action
   * @return
   */
  private boolean containAction(List<Action> actions, Action action) {
    boolean found = false;
    for (Action src : actions) {
      if (Objects.equals(action.getClass().getName(), src.getClass().getName())) {
        if (action instanceof CouponAction) {
          CouponAction ca = (CouponAction) action;
          if (Objects.equals(ca.getActivity().getUuid(),
              ((CouponAction) src).getActivity().getUuid())) {
            found = true;
            break;
          }
        } else if (action instanceof GoodsAction) {
          GoodsAction ga = (GoodsAction) action;
          if (Objects.equals(ga.getGoods().getUuid(), ((GoodsAction) src).getGoods().getUuid())) {
            found = true;
            break;
          }
        } else if (action instanceof PrizeAction) {
          PrizeAction pa = (PrizeAction) action;
          if (Objects.equals(pa.getPrize().getUuid(), ((PrizeAction) src).getPrize().getUuid())) {
            found = true;
            break;
          }
        } else if (action instanceof UseCouponAction) {
          UseCouponAction uca = (UseCouponAction) action;
          if (Objects.equals(uca.getActivity().getUuid(),
              ((UseCouponAction) src).getActivity().getUuid())) {
            found = true;
            break;
          }
        } else {
          found = true;
          break;
        }
      }
    }
    return found;
  }

  /**
   * 计算促销表达式。
   * 
   * @param exp
   * @param context
   * @return
   * @throws PromotionException
   */
  private ExpCalcResult calcExp(Expression exp, Context context) throws PromotionException {
    if (exp instanceof IfThenExpression) {
      IfThenExpression ifThenExp = (IfThenExpression) exp;
      final ConditionResult cr = accept(ifThenExp.getCondition(), context);
      // 记录表达式计算结果
      context.addTrace(ifThenExp, cr);

      List<Action> actions = Lists.newArrayList();
      if (cr.isAccept()) {
        if (cr.supportsStep()) {
          actions = stepActions(ifThenExp.getActions(), cr.getStep());
        } else {
          // 如果不支持叠加，则直接返回原促销结果
          actions = ifThenExp.getActions();
        }
      }
      return new ExpCalcResult(cr, actions);

    } else if (exp instanceof CaseExpression) {
      CaseExpression caseExp = (CaseExpression) exp;

      ExpCalcResult er = ExpCalcResult.refuse();
      for (IfThenExpression ifThenExp : caseExp.getCases()) {
        er = calcExp(ifThenExp, context);
        if (er.getResult().isAccept()) {
          // case语句必须顺序执行，当接受其中一个时就中断执行
          break;
        }
      }

      // 记录表达式计算结果
      context.addTrace(caseExp, er.getResult());
      return er;

    } else {
      throw new PromotionException("不支持的促销表达式: " + exp.getClass().getName());
    }
  }

  /**
   * 按指定的叠加步长计算促销结果。
   * 
   * @param actions
   * @param step
   * @return
   */
  private List<Action> stepActions(List<Action> actions, BigDecimal step) {
    List<Action> stepActions = Lists.newArrayList();
    for (Action action : actions) {
      stepActions.addAll(action.stepActions(step));
    }
    return stepActions;
  }

  /**
   * 取得所有生效的促销单
   * 
   * @return
   */
  private List<PromotionBill> getAllBills(Context context) throws PromotionException {
    final OrderBill order = (OrderBill) context.get(Context.KEY_ORDER);
    if (order == null) {
      throw new PromotionException("没有设置促销订单");
    }

    PromotionBillFilter filter = new PromotionBillFilter();
    filter.setStateIn(new PromotionBillState[] {
        PromotionBillState.submit });
    // filter.setOrgIdEquals(Long.valueOf(order.getOwnerOrg()));
    filter.setOrderField("updateDate");
    filter.setOrderDirection(QueryOrderDirection.desc);
    filter.setPageSize(PromotionBillFilter.SEARCH_ALL_PAGE_SIZE);
    PageResult<PromotionBill> results = billService.query(filter);
    return results.getRecords();
  }

  /**
   * 处理冲突
   * 
   * @param actions
   */
  private List<Action> resolveConflicts(List<Action> actions) {
    if (logger.isDebugEnabled()) {
      try {
        logger.debug("解决冲突前的促销结果: {}", mapper.writeValueAsString(actions));
      } catch (Exception e) {
      }
    }

    // 先排序
    sortActionsByPriority(actions);

    // 构建冲突表
    Map<String, Boolean> conflictMap = Maps.newHashMap();
    for (Action a : actions) {
      for (String b : a.getConflicts()) {
        conflictMap.put(a.getType() + "_" + b, true);
        conflictMap.put(b + "_" + a.getType(), true);
      }
    }

    // 去掉冲突
    List<Action> results = Lists.newArrayList();
    for (Action action : actions) {
      if (!isConflict(action, results, conflictMap)) {
        results.add(action);
      }
    }

    if (logger.isDebugEnabled()) {
      try {
        logger.debug("解决冲突后的促销结果: {}", mapper.writeValueAsString(actions));
      } catch (Exception e) {
      }
    }

    return results;
  }

  /**
   * 按优先级倒序排序。
   * 
   * @param actions
   */
  private static void sortActionsByPriority(List<Action> actions) {
    Collections.sort(actions, new Comparator<Action>() {

      @Override
      public int compare(Action o1, Action o2) {
        return o2.getPriority() - o1.getPriority();
      }

    });
  }

  /**
   * 是否冲突
   * 
   * @param action
   * @param actions
   * @param conflictMap
   * @return
   */
  private boolean isConflict(Action action, List<Action> actions,
      Map<String, Boolean> conflictMap) {
    for (Action a : actions) {
      if (conflictMap.containsKey(a.getType() + "_" + action.getType())
          || conflictMap.containsKey(action.getType() + "_" + a.getType())) {
        return true;
      }
    }
    return false;
  }

  /**
   * 输出计算结果。
   * 
   * @param promBill
   */
  private void printCalcResult(PromotionBill promBill, Context context) {
    tl.set(context);
    try {
      logger.debug("促销单 {} 计算结果\n{}", promBill.getId(),
          mapper.writerWithDefaultPrettyPrinter().writeValueAsString(promBill));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      tl.remove();
    }
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    mapper = createMapper();
  }

  /**
   * @return
   */
  private ObjectMapper createMapper() {
    JsonMapperConfigurator mapperCfg = new JsonMapperConfigurator(null, null);
    MyBeanSerializerFactory serializerFactory = new MyBeanSerializerFactory(
        new BeanSerializerListener() {

          @Override
          public void postSerialization(Object value, JsonGenerator jgen) throws IOException {
            if (value instanceof Condition || value instanceof Expression) {
              Context context = tl.get();
              if (context != null) {
                jgen.writeObjectField("calcResult", context.getTrace(value));
              }
            }
          }
        });
    ObjectMapper mapper = mapperCfg.getDefaultMapper().setSerializerFactory(serializerFactory);
    mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    return mapper;
  }

}
