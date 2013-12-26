package com.bayaran.service.configuration;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import com.bayaran.dao.AppUserClient;
import com.bayaran.service.UsersResource;

@Configuration
@Import({ DaoConfig.class })
public class ResourceConfig {	
	@Bean
	@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
	public UsersResource userResource(final AppUserClient appUserClient) {
		return new UsersResource(appUserClient);
	}
}
