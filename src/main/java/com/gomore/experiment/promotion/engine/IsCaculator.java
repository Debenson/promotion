/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	IsPromotionEngine.java
 * 模块说明：	
 * 修改历史：
 * 2016年8月10日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 标识促销条件计算器。
 * 
 * @author Debenson
 * @since 0.1
 */
@Target({
    ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface IsCaculator {

}
