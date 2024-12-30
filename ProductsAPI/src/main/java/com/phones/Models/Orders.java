package com.phones.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Orders")
public class Orders {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sno")
	private int sno;
	@Column(name="orderid")
	private long orderid;
	@Column(name="productid")
	private int productid;
	@Column(name="quantity")
	private int quantity;
	@Column(name="totalprice")
	private double totalprice;
	@Column(name="purchasetime")
	private LocalDateTime now;
	
	public Orders() {
		super();
	}

	public Orders(int sno, int orderid, int productid, int quantity, double totalprice, LocalDateTime now) {
		super();
		this.orderid = orderid;
		this.productid = productid;
		this.quantity = quantity;
		this.totalprice = totalprice;
		this.now = now;
	}

	public long getOrderid() {
		return orderid;
	}
	public void setOrderid(long orderidcounter) {
		this.orderid = orderidcounter;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public LocalDateTime getNow() {
		return now;
	}
	public void setNow(LocalDateTime now) {
		this.now = LocalDateTime.now();
	}
}
