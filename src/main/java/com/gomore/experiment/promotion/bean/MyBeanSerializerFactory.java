package com.gomore.experiment.promotion.bean;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;

/**
 * @author Debenson
 * @since 0.1
 */
public class MyBeanSerializerFactory extends BeanSerializerFactory {
  private static final long serialVersionUID = 7125373260695300105L;

  private final BeanSerializerListener serializerListener;

  public MyBeanSerializerFactory(final BeanSerializerListener serializerListener) {
    super(null);
    this.serializerListener = serializerListener;
  }

  @Override
  protected BeanSerializerBuilder constructBeanSerializerBuilder(BeanDescription beanDesc) {
    return new MyBeanSerializerBuilder(beanDesc, serializerListener);
  }

}
