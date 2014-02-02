package com.syedatifakhtar.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="order", catalog = "atif")
public class Order implements Serializable{
	
	private long orderID;
	private String personName;
	private String personPhone;
	private Date createdDate;
	private long total;
	
	private Set<CheeseOrder> cheeseOrder	=	new HashSet<CheeseOrder>(0);
	
	public Order() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	public long getOrderID() {
		return orderID;
	}

	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}
	
	@Column(name="ORDEREDBYPERSON")
	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	@Column(name="PHONENUMBER")
	public String getPersonPhone() {
		return personPhone;
	}

	public void setPersonPhone(String personPhone) {
		this.personPhone = personPhone;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE", nullable=false)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	@OneToMany(fetch=FetchType.EAGER,mappedBy="pk.order",cascade=CascadeType.ALL)
	public Set<CheeseOrder> getCheeseOrder() {
		return cheeseOrder;
	}

	public void setCheeseOrder(Set<CheeseOrder> CheeseOrder) {
		this.cheeseOrder = CheeseOrder;
	}

	@Column(name="TOTAL")
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

}
