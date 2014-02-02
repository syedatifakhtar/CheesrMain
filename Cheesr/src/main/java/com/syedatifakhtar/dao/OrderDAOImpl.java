package com.syedatifakhtar.dao;

import java.util.List;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.syedatifakhtar.model.Order;

public class OrderDAOImpl implements OrderDAO{

	
	private SessionFactory mySessionFactory;
	
	@Override
	public List<Order> findall() {
		Session	session = mySessionFactory.getCurrentSession();
		List orders	=	session.createQuery("from Order").list();
		return (List<Order>)orders;
	}

	@Override
	public Order getOrder(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long saveOrder(Order order) {
		Session session = mySessionFactory.getCurrentSession();
		Long generatedId=(Long)session.save(order);
		return generatedId.longValue();
	}

	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	public SessionFactory getMySessionFactory() {
		return mySessionFactory;
	}

	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}

}
