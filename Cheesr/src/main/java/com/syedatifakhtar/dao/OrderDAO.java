package com.syedatifakhtar.dao;

import java.util.List;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.hibernate.SessionFactory;

import com.syedatifakhtar.model.Order;

public interface OrderDAO {

	/*
	 * @return List<Order>
	 */
	List<Order> findall();
	Order getOrder(long id);
	long saveOrder(Order order);
	void updateOrder(Order order);
	void deleteOrder(Order order);

}
