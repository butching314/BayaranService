package com;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bayaran.configuration.AppConfig;

public class FooTest {
	@Test
	public void test() throws Exception {
//		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				"spring/application.xml");
		ApplicationContext ctx = 
				   new AnnotationConfigApplicationContext(AppConfig.class);
		
		System.out.println("RUNNING TEST");
		FooBean bean = ctx.getBean(FooBean.class);
		System.out.println("BEAN: " + bean);
		
		FooBean beanx = ctx.getBean(FooBean.class);
		System.out.println("BEAN: " + beanx);
	}
}
