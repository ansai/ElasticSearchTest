package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@ComponentScan(basePackages = {"com.example.demo"})
public class MockApplication implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  @Autowired
  private ApplicationContext applicationContext;

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.context.ApplicationContextInitializer#initialize(org.springframework.
   * context.ConfigurableApplicationContext)
   */
  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {
    // Register the AutowiredAnnotationBeanPostProcessor while initalizing
    // the context so we get there before any @Autowire resolution happens
    // We set the "requiredParameterValue" so that @Autowire fields are not
    // required to be resolved. Very useful for a test context
    GenericApplicationContext ctx = (GenericApplicationContext) applicationContext;
    ctx.registerBeanDefinition(AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME,
        BeanDefinitionBuilder.rootBeanDefinition(AutowiredAnnotationBeanPostProcessor.class)
            .addPropertyValue("requiredParameterValue", false).getBeanDefinition());

  }

//  @Bean
//  public HttpClient httpClient() {
//    new ApplicationContextProvider().setApplicationContext(applicationContext);
//    return new HttpClient();
//  }

  @Bean
  public ObjectMapper defaultObjectMapper() {
    return new ObjectMapper();
  }

}
