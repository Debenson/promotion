/**
 * 版权所有(C)，上海勾芒信息科技，2016，所有权利保留。
 * 
 * 项目名：	gomore-promotion
 * 文件名：	ScriptCondition.java
 * 模块说明：	
 * 修改历史：
 * 2016年10月13日 - Debenson - 创建。
 */
package com.gomore.experiment.promotion.model.condition;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 使用脚本计算促销条件。 <br>
 * 脚本最终要求返回Boolean值，表示是否接受促销条件。
 * 
 * @author Debenson
 * @since 0.1
 */
@XmlRootElement
public class ScriptCondition extends AbstractCondition {
  private static final long serialVersionUID = -7466785310167386785L;

  public static final String CTYPE = "scriptCondition";

  private String lang;
  private String script;

  @Override
  public String getType() {
    return CTYPE;
  }

  /** 语言 */
  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  /** 脚本 */
  public String getScript() {
    return script;
  }

  public void setScript(String script) {
    this.script = script;
  }

}
