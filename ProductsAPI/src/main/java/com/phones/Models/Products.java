package com.phones.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Products")
public class Products {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="productid")
	private int productid;
	@Column(name="productname")
	private String productname;
	@Column(name="productdescription")
	private String productdescription;
	@Column(name="productquantity")
	private int productquantity;
	@Column(name="productprice")
	private double productprice;
	
	@ManyToOne
	@JoinColumn(name="productbrand")
	private Brand brand;
	
	public Products(int productid, String productname, String productdescription, int productquantity,
			double productprice, Brand brand) {
		super();
		this.productid = productid;
		this.productname = productname;
		this.productdescription = productdescription;
		this.productquantity = productquantity;
		this.productprice = productprice;
		this.brand = brand;
	}
	
	public Products() {
		super();
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getProductdescription() {
		return productdescription;
	}

	public void setProductdescription(String productdescription) {
		this.productdescription = productdescription;
	}

	public int getProductquantity() {
		return productquantity;
	}

	public void setProductquantity(int productquantity) {
		this.productquantity = productquantity;
	}

	public double getProductprice() {
		return productprice;
	}

	public void setProductprice(double productprice) {
		this.productprice = productprice;
	}

	public String getBrand() {
		return brand.getBrandname();
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

}
