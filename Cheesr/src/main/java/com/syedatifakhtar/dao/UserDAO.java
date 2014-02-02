package com.syedatifakhtar.dao;

import java.util.List;

import com.syedatifakhtar.model.User;

public interface UserDAO {

	List<User> findall();
	User getUser(long id);
	long saveUser(User user);
	void updateUser(User user);
	
}
