package com.syedatifakhtar.service;

import java.util.List;
import java.util.Map;

import com.syedatifakhtar.model.Cheese;
import com.syedatifakhtar.model.Order;

public interface CheeseOrderService {
	
	public List<Order> findall();
	public Order getOrder(long id);
	public long saveOrder(Order order,Map<Cheese,Integer> cheeseQuantityMap);
	public void updateOrder(Order order);
	public void deleteOrder(Order order);
}
