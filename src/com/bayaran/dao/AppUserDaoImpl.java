package com.bayaran.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AppUserDaoImpl extends HibernateDaoSupport implements AppUserDao {

	@Override
	public void save(AppUser appUser) {
		getHibernateTemplate().save(appUser);
	}
	
}
