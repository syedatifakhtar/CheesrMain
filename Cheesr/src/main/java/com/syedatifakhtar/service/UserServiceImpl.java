package com.syedatifakhtar.service;

import java.util.List;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.transaction.annotation.Transactional;

import com.syedatifakhtar.dao.UserDAO;
import com.syedatifakhtar.model.User;

public class UserServiceImpl implements UserService{
	
	@SpringBean(name="userDAO")
	private UserDAO userDAO;
	
	@Transactional
	@Override
	public List<User> findall() {
		return userDAO.findall();
	}
	
	@Transactional
	@Override
	public User getUser(long id) {
		return userDAO.getUser(id);
	}

	@Transactional
	@Override
	public long saveUser(User user) {
		return userDAO.saveUser(user);
	}

	@Transactional
	@Override
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	
}
