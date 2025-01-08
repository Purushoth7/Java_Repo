package com.phones.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.phones.Models.Brand;
import com.phones.Models.Products;
import com.phones.Models.users;
import com.phones.Repositories.UserRepo;
import com.phones.Repositories.repobrand;
import com.phones.Repositories.repoproducts;

@Service
public class service {
	
	@Autowired
	private repobrand repo1;
	
	@Autowired
	private repoproducts repo2;
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private AuthenticationManager authmanager;
	
	@Autowired
	private JWTService jwtservice;
	
	private BCryptPasswordEncoder passencoder=new BCryptPasswordEncoder(12);
	
	public users register(users user) {
		user.setPassword(passencoder.encode(user.getPassword()));
		return userrepo.save(user);
	}
	
	public String verifyuser(users user) {
		Authentication auth=authmanager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if(auth.isAuthenticated()) {
			return jwtservice.generatetoken(user.getUsername());
		}
		return "fail";
	}
	
	public List<Brand> getallbybrand(){
		return repo1.findAll();
	}
	
	public List<Products> getallproducts(){
		return repo2.findAll();
	}
	
	public List<Brand> getbybrandname(String Brandname) {
		return repo1.findByBrandname(Brandname);
	}
	
	public String totalproducts() {
		List<Products> prod=repo2.findAll();
		int total = 0;
		for(Products p:prod) {
			total++;
		}
		return "total products = "+total;
	}
	
	public Products addproduct(Products product) {
		repo2.save(product);
		return product;
	}
	
	public Products updateproductquantity(String productname, int quantity) {
		List<Products> prod=repo2.findByProductname(productname);
		Products product = null;
		for(Products p:prod) {
			if(p.getProductname().equals(productname)) {
				p.setProductquantity(quantity);
				product=p;
				repo2.save(product);
			}
		}
		return product;
	}
	
	public String deleteproduct(String productname) {
		List<Products> prod=repo2.findByProductname(productname);
		String delmsg=productname+" not found";
		for(Products p:prod) {
			if(p.getProductname().equals(productname)) {
				repo2.deleteById(p.getProductid());
				delmsg=productname+" deleted successfully";
				//break;
			}
		}
		return delmsg;
	}
}
