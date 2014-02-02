package com.syedatifakhtar.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="CHEESEORDER",catalog="atif")
@AssociationOverrides({
	@AssociationOverride(name="pk.order",joinColumns=@JoinColumn(name="ORDER_ID")),
	@AssociationOverride(name="pk.cheese",joinColumns=@JoinColumn(name="CHEESE_ID"))
})
public class CheeseOrder implements Serializable{

	private CheeseOrderID pk	=	new CheeseOrderID();
	private int quantity;
	private int itemPrice;
	

	@EmbeddedId
	public CheeseOrderID getPk() {
		return pk;
	}
	
	public void setPk(CheeseOrderID pk) {
		this.pk=pk;
	}
	
	@Transient
	public Cheese getCheese() {
		return getPk().getCheese();
	}

	
	public void setCheese(Cheese cheese) {
		getPk().setCheese(cheese);
	}

	@Transient
	public Order getOrder() {
		return getPk().getOrder();
	}
	
	public void setOrder(Order order) {
		getPk().setOrder(order);
	}
	
	@Column(name="QUANTITY",nullable=false)
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Column(name="ITEMPRICE", nullable=false)
	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public boolean equals(Object o) {
		if(this==o) 
			return true;
		
		if(o==null || getClass()!=o.getClass())
			return false;
		
		CheeseOrder other	=	(CheeseOrder) o;
		
		if(getPk()!=null ? !getPk().equals(other.getPk()):other.getPk()!=null)
			return false;
		return true;
	}
	
	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
	
}
