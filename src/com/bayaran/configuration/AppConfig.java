package com.bayaran.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.FooBean;

@Configuration
public class AppConfig {
	@Bean
	public FooBean fooBean() {
		System.out.println("FOOBEAN");
		return new FooBean();
	}
	
}
