package com.phones.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="orderidandvalue")
public class orderidandvalue {
	
	@Id
	@Column(name="orderid")
	private long orderid;
	@Column(name="quantity")
	private int quantity;
	@Column(name="totalordervalue")
	private double totalordervalue;
	
	public orderidandvalue() {
		super();
	}

	public orderidandvalue(long orderidcounter, int quantity, double totalordervalue) {
		super();
		this.orderid = orderidcounter;
		this.quantity=quantity;
		this.totalordervalue = totalordervalue;
	}
	
	public long getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalordervalue() {
		return totalordervalue;
	}
	public void setTotalordervalue(double totalordervalue) {
		this.totalordervalue = totalordervalue;
	}
}
