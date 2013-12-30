package com.bayaran.dao;

import java.util.List;

import com.bayaran.domain.User;

public interface AppUserDaoClient {
	List<User> fetchAllUsers() throws DaoException;
}
