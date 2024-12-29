package com.example.country.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Country")
public class Country {
	
	//data storing variables for db
	@Id
	@Column(name="id")
	int id;
	
	@Column(name="Cname")
	String Cname;
	
	@Column(name="Capital")
	String Capital;
	
	public Country() {
		
	}
	
	public Country(int id, String cname, String capital) {
		this.id = id;
		this.Cname = cname;
		this.Capital = capital;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCname() {
		return Cname;
	}

	public void setCname(String cname) {
		Cname = cname;
	}

	public String getCapital() {
		return Capital;
	}

	public void setCapital(String capital) {
		Capital = capital;
	}

}
