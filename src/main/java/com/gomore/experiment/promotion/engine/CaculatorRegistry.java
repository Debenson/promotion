/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	PromotionEngineFactory.java
 * 模块说明：	
 * 修改历史：
 * 2016年8月9日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.gomore.experiment.promotion.model.condition.Condition;
import com.google.common.collect.Lists;

/**
 * 促销条件计算器注册器。
 * 
 * @author Debenson
 * @since 0.1
 */
@Component
public class CaculatorRegistry implements BeanPostProcessor {
  private static final Logger logger = LoggerFactory.getLogger(CaculatorRegistry.class);

  private static List<Caculator<Condition>> caculators = Lists.newArrayList();

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if (bean.getClass().isAnnotationPresent(IsCaculator.class)) {
      @SuppressWarnings("unchecked")
      Caculator<Condition> caculator = (Caculator<Condition>) bean;
      logger.debug("发现促销条件计算器: " + caculator.getClass().getName());
      caculators.add(caculator);
    }
    return bean;
  }

  public List<Caculator<Condition>> getCaculators() {
    return caculators;
  }

}
