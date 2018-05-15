package com.gomore.experiment.promotion.bean;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;

/**
 * @author Debenson
 * @since 0.1
 */
public class MyBeanSerializer extends BeanSerializer {
  private static final long serialVersionUID = 8596931377685800615L;

  private final BeanSerializerListener serializerListener;

  protected MyBeanSerializer(BeanSerializerBase src, BeanSerializerListener serializerListener) {
    super(src);
    this.serializerListener = serializerListener;
  }

  @Override
  protected void serializeFields(Object bean, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    super.serializeFields(bean, gen, provider);
    if (serializerListener != null) {
      serializerListener.postSerialization(bean, gen);
    }
  }

}
