package com.gomore.experiment.promotion.bean;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;

/**
 * @author Debenson
 * @since 0.1
 */
public class MyBeanSerializerBuilder extends BeanSerializerBuilder {
  private final BeanSerializerListener serializerListener;

  public MyBeanSerializerBuilder(final BeanDescription beanDesc,
      final BeanSerializerListener serializerListener) {
    super(beanDesc);
    this.serializerListener = serializerListener;
  }

  @Override
  public JsonSerializer<?> build() {
    BeanSerializerBase serializer = (BeanSerializerBase) super.build();
    return new MyBeanSerializer(serializer, serializerListener);
  }

}
