package com.syedatifakhtar.service;

import java.util.List;

import com.syedatifakhtar.model.User;

public interface UserService {

	public List<User> findall();
	public User getUser(long id);
	public long saveUser(User user);
	public void updateUser(User user);
}
