package com.syedatifakhtar.cart;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.syedatifakhtar.model.Cheese;
import com.syedatifakhtar.model.Order;

public interface CheesrCart extends Serializable {

	public void addToCart(Cheese cheese,int quantity);

	public void removeFromCart(Cheese cheese);
	
	public Order getOrder();
	
	public Map<Cheese,Integer> getCart();
}
