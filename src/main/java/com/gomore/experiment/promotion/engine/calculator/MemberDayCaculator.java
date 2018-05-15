/**
 * 版权所有(C)，上海勾芒信息科技，2017，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	MemberScoreCaculator.java
 * 模块说明：	
 * 修改历史：
 * 2017年8月28日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.engine.calculator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.gomore.experiment.promotion.engine.ConditionResult;
import com.gomore.experiment.promotion.engine.IsCaculator;
import com.gomore.experiment.promotion.model.Context;
import com.gomore.experiment.promotion.model.condition.mbr.MemberDayCondition;
import com.gomore.experiment.promotion.model.condition.mbr.MemberDayCondition.DateUnit;
import com.gomore.experiment.promotion.model.condition.mbr.MemberDayCondition.MemberDay;
import com.gomore.experiment.promotion.service.bean.Member;

import lombok.extern.slf4j.Slf4j;

/**
 * 会员日促销条件计算器
 * 
 * @author Debenson
 * @since 0.1
 */
@IsCaculator
@Slf4j
public class MemberDayCaculator extends CaculatorSupport<MemberDayCondition> {

  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd");

  public MemberDayCaculator() {
    super(MemberDayCondition.class);
  }

  @Override
  public ConditionResult accept(MemberDayCondition condition, Context context) {
    final Member mbr = getMember(context);
    if (mbr == null) {
      return ConditionResult.accept(false);
    }

    boolean accept = false;
    DateTime now = new DateTime();
    final String dateOfYear = DATE_FORMAT.format(new Date());

    try {
      for (MemberDay mbrDay : condition.getDays()) {
        if (DateUnit.DAY_OF_WEEK.equals(mbrDay.getUnit())) {
          accept = (now.getDayOfWeek() == Integer.valueOf(mbrDay.getValue()).intValue());

        } else if (DateUnit.DAY_OF_MONTH.equals(mbrDay.getUnit())) {
          accept = (now.getDayOfMonth() == Integer.valueOf(mbrDay.getValue()).intValue());

        } else if (DateUnit.MONTH_OF_YEAR.equals(mbrDay.getUnit())) {
          accept = (now.getMonthOfYear() == Integer.valueOf(mbrDay.getValue()).intValue());

        } else if (DateUnit.DATE_OF_YEAR.equals(mbrDay.getUnit())) {
          String dateStr = mbrDay.getValue();
          if (StringUtils.isNotBlank(dateStr) && dateStr.length() > 5) {
            dateStr = dateStr.substring(5);
          }
          accept = Objects.equals(dateOfYear, dateStr);

        } else {
          log.error("无法识别的会员日单位: {}", mbrDay.getUnit());
        }
        if (accept) {
          break;
        }
      }
    } catch (Exception e) {
      log.error("", e);
    }
    return ConditionResult.accept(accept);
  }

}
