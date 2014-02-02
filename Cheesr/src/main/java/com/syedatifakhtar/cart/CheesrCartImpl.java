package com.syedatifakhtar.cart;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.syedatifakhtar.model.Cheese;
import com.syedatifakhtar.model.Order;

public class CheesrCartImpl implements CheesrCart{
	
	private Order order;
	private Map<Cheese,Integer> cheeseQuantityMap;
	
	public CheesrCartImpl() {
		init();
		createDummyOrderInfo();
	}

	private void init() {
		order	=	new Order();
		cheeseQuantityMap	=	new HashMap<Cheese,Integer>(0);
	}
	private void createDummyOrderInfo() {
		order.setCreatedDate(new Date());
		order.setPersonName("Atif");
		order.setPersonPhone("7840847298");
	}
	
	public void addToCart(Cheese cheese,int quantity) {
		cheeseQuantityMap.put(cheese, new Integer(quantity));
	}

	public void removeFromCart(Cheese cheese) {
		cheeseQuantityMap.remove(cheese);
	}

	@Override
	public Order getOrder() {
		return order;
	}

	@Override
	public Map<Cheese, Integer> getCart() {
		return cheeseQuantityMap;
	}
	
	
}
