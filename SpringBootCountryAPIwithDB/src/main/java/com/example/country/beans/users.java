package com.example.country.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class users {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sno;
	private String username;
	private String emailid;
	private String password;
	
	public users() {
		super();
	}
	
	public users(int sno, String username, String emailid, String password) {
		super();
		this.sno = sno;
		this.username = username;
		this.emailid = emailid;
		this.password = password;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
