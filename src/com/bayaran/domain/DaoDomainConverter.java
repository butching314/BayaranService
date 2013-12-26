package com.bayaran.domain;

import java.util.List;

import org.apache.commons.lang3.Validate;

import com.bayaran.dao.AppUser;
import com.google.common.collect.ImmutableList;

public final class DaoDomainConverter {
	public static List<User> toDomainUserList(final List<AppUser> appUserList) {
		Validate.notNull(appUserList);
		
		final ImmutableList.Builder<User> appUserListBuilder = ImmutableList.builder();		
		for (final AppUser appUser : appUserList) {
			appUserListBuilder.add(toDomainUser(appUser));
		}
		
		return appUserListBuilder.build();
	}
	
	public static User toDomainUser(final AppUser appUser) {
		Validate.notNull(appUser);
		
		final User user = new User();
		user.setUserId(appUser.getUserId());
		user.setFirstName(appUser.getFirstName());
		user.setLastName(appUser.getLastName());
		
		return user;
	}
}
