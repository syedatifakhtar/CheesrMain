package com.syedatifakhtar.dao;

import java.util.List;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.syedatifakhtar.model.User;

public class UserDAOImpl implements UserDAO {

	@SpringBean(name="mySessionFactory")
	private SessionFactory mySessionFactory;
	
	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}

	@Override
	public List<User> findall() {
		Session session = mySessionFactory.getCurrentSession();
		List users=session.createQuery("from User").list();
		return (List<User>)users;
	}
	
	@Override
	public User getUser(long id) {
		Session session = mySessionFactory.getCurrentSession();
		User user=(User)session.get(User.class, new Long(id));
		return null;
	}
	
	@Override
	public long saveUser(User user) {
		Session session = mySessionFactory.getCurrentSession();
		Long generatedID = (Long)session.save(user);
		return generatedID.longValue();
	}
	@Override
	public void updateUser(User user) {
		Session session=mySessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}
}
