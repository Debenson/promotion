/**
 * 版权所有(C)，上海勾芒信息科技，2017，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	Member.java
 * 模块说明：	
 * 修改历史：
 * 2017年8月28日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.service.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.gomore.experiment.promotion.common.UCN;

/**
 * 促销会员信息
 * 
 * @author Debenson
 * @since 0.1
 */
public class Member extends UCN {
  private static final long serialVersionUID = 7104072997289808835L;

  private BigDecimal score;
  private UCN grade;
  private Date birthday;

  /** 会员积分 */
  public BigDecimal getScore() {
    return score;
  }

  public void setScore(BigDecimal score) {
    this.score = score;
  }

  /** 会员等级 */
  public UCN getGrade() {
    return grade;
  }

  public void setGrade(UCN grade) {
    this.grade = grade;
  }

  /** 会员生日 */
  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

}
