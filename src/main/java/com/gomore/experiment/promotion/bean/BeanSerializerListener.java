package com.gomore.experiment.promotion.bean;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;

/**
 * 用来监听Bean序列化事件
 * 
 * @author Debenson
 * @since 0.1
 */
public interface BeanSerializerListener {

  /**
   * 在序列化完成前
   * 
   * @param value
   * @param jgen
   * @throws IOException
   */
  void postSerialization(Object value, JsonGenerator jgen) throws IOException;

}
