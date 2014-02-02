package com.syedatifakhtar.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class CheeseOrderID implements Serializable{

	private Order order;
	private Cheese cheese;
	
	@ManyToOne
	public Cheese getCheese() {
		return cheese;
	}
	public void setCheese(Cheese cheese) {
		this.cheese = cheese;
	}
	
	@ManyToOne
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	public boolean equals(Object o) {
		if(this==o)
			return true;
		if(o==null || getClass()!=o.getClass()) return false;
		
		CheeseOrderID other	=	(CheeseOrderID) o;
		
		if(order!=null?!order.equals(other.order):other.order!=null)
		return false;
		
		if(cheese!=null?!cheese.equals(other.cheese):other.cheese!=null)
			return false;
		
		return true;
	}
	
	public int hashCode() {
		int result;
		result=(order!=null?order.hashCode():0);
		result=41 * result + (cheese!=null?cheese.hashCode():0);
		return result;
	}
	
}
