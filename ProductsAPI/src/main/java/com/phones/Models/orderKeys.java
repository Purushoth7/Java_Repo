package com.phones.Models;

public class orderKeys {
	
	private String productname;
	private int quantity;
	
	public orderKeys(String productname, int quantity) {
		super();
		this.productname = productname;
		this.quantity = quantity;
	}
	
	public orderKeys() {
		super();
	}

	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
