package com.bayaran.service.configuration;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.bayaran.dao.TestDao;
import com.bayaran.service.HelloWorldService;


@Configuration
public class AppConfig {

	@Bean
	public TestDao testDao() {
		System.out.println("Constructing DAO");
		return new TestDao();
	}
	
	@Bean
	@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
	public HelloWorldService helloWorldService(final TestDao testDao) {
		System.out.println("Constructing HWS");
		
		return new HelloWorldService(testDao);
	}
}
