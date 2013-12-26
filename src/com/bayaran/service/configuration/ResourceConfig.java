package com.bayaran.service.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import com.bayaran.service.UsersResource;

@Configuration
@Import({ DaoConfig.class })
public class ResourceConfig {	
	@Bean
	@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
	public UsersResource userResource(final DataSource dataSource) {
		System.out.println("Construct Resource");
		return new UsersResource(dataSource);
	}
}
