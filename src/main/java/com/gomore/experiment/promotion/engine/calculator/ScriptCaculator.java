/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	GroovyScriptCaculator.java
 * 模块说明：	
 * 修改历史：
 * 2016年10月13日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine.calculator;

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.gomore.experiment.promotion.engine.ConditionResult;
import com.gomore.experiment.promotion.engine.IsCaculator;
import com.gomore.experiment.promotion.model.Context;
import com.gomore.experiment.promotion.model.condition.ScriptCondition;

/**
 * 脚本引擎计算器。
 * 
 * @author Debenson
 * @since 0.1
 */
@IsCaculator
public class ScriptCaculator extends CaculatorSupport<ScriptCondition>
    implements ApplicationContextAware {
  private static final Logger logger = LoggerFactory.getLogger(ScriptCaculator.class);

  private static final String ENV_SPRING_CONTEXT = "appCtx";
  private static final String ENV_PROMOTION_CONTEXT = "contxt";

  public static void main(String[] args) {
    ScriptEngineManager manager = new ScriptEngineManager();
    List<ScriptEngineFactory> factories = manager.getEngineFactories();
    for (ScriptEngineFactory factory : factories) {
      System.out.printf(
          "Name: %s%nVersion: %s%n" + "Language name: %s%n" + "Language version: %s%n"
              + "Extensions: %s%n" + "Mime types: %s%n" + "Names: %s%n",
          factory.getEngineName(), factory.getEngineVersion(), factory.getLanguageName(),
          factory.getLanguageVersion(), factory.getExtensions(), factory.getMimeTypes(),
          factory.getNames());
      System.out.println();
      System.out.println();
      System.out.println();
      // ScriptEngine engine = factory.getScriptEngine();
    }
  }

  public ScriptCaculator() {
    super(ScriptCondition.class);
  }

  @Override
  public ConditionResult accept(ScriptCondition condition, Context context) {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName(condition.getLang());

    boolean accept = false;
    try {
      engine.put(ENV_SPRING_CONTEXT, this.appCtx);
      engine.put(ENV_PROMOTION_CONTEXT, context);
      Boolean ret = (Boolean) engine.eval(condition.getScript());
      accept = (ret == null ? false : ret.booleanValue());
    } catch (ScriptException e) {
      logger.error("", e);
    }
    return ConditionResult.accept(accept);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.appCtx = applicationContext;
  }

  private ApplicationContext appCtx;

}
