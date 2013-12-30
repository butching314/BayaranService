package com.bayaran.service.configuration;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import com.bayaran.dao.AppUserDaoClient;
import com.bayaran.dao.ExpenseDaoClient;
import com.bayaran.service.ExpenseResource;
import com.bayaran.service.UserResource;

@Configuration
@Import({ DaoConfig.class })
public class ResourceConfig {	
	@Bean
	@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
	public UserResource userResource(final AppUserDaoClient appUserDaoClient) {
		return new UserResource(appUserDaoClient);
	}
	
	@Bean
	@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
	public ExpenseResource expenseResource(final ExpenseDaoClient expenseDaoClient) {
		return new ExpenseResource(expenseDaoClient);
	}
}
