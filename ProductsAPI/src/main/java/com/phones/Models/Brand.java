package com.phones.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Brand")
public class Brand {
	
	@Id
	@Column(name="brandname")
	private String brandname;
	@Column(name="servicelocation")
	private String servicelocation;
	@Column(name="contactno")
	private long contactno;
	
	@OneToMany(mappedBy="brand", cascade=CascadeType.ALL)
	private List<Products> products;
	
	public Brand(String brandname, String servicelocation, long contactno, List<Products> products) {
		super();
		this.brandname = brandname;
		this.servicelocation = servicelocation;
		this.contactno = contactno;
		this.products = products;
	}
	
	public Brand() {
		super();
	}

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public String getServicelocation() {
		return servicelocation;
	}
	public void setServicelocation(String servicelocation) {
		this.servicelocation = servicelocation;
	}
	public long getContactno() {
		return contactno;
	}
	public void setContactno(long contactno) {
		this.contactno = contactno;
	}

}
