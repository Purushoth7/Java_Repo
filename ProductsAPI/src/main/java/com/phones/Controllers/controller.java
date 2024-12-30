package com.phones.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phones.Models.Brand;
import com.phones.Models.Products;
import com.phones.Models.users;
import com.phones.Services.service;

@RestController
public class controller {
	
	@Autowired
	private service serv;
	
	@PostMapping("/register")
	public users register(@RequestBody users user) {
		return serv.register(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody users user) {
		return serv.verifyuser(user);
	}
	
	@GetMapping("/brands")
	public ResponseEntity<List<Brand>> getallbybrand() {
		try {
			List<Brand> brands=serv.getallbybrand();
			return new ResponseEntity<List<Brand>>(brands, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Products>> getallproducts() {
		try {
			List<Products> products=serv.getallproducts();
			return new ResponseEntity<List<Products>>(products, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/products/{brandname}")
	public ResponseEntity<List<Brand>> getbybrandname(@PathVariable String brandname) {
		try {
			List<Brand> brand=serv.getbybrandname(brandname);
			return new ResponseEntity<List<Brand>>(brand, HttpStatus.FOUND);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/totalproducts")
	public ResponseEntity<String> totalproducts() {
		try {
			String total=serv.totalproducts();
			return new ResponseEntity<String>(total, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/addproduct")
	public ResponseEntity<Products> addproduct(@RequestBody Products product) {
		try {
			Products addedproduct=serv.addproduct(product);
			return new ResponseEntity<Products>(addedproduct, HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/quantityupdate")
	public ResponseEntity<Products> updateproductquantity(@RequestParam(name="productname") String productname, @RequestParam(name="quantity") int quantity) {
		try {
			Products updatedproduct=serv.updateproductquantity(productname, quantity);
			return new ResponseEntity<Products>(updatedproduct, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/deleteproduct")
	public ResponseEntity<String> deleteproduct(@RequestParam(name="productname") String productname) {
		try {
			String deletedproduct=serv.deleteproduct(productname);
			return new ResponseEntity<String>(deletedproduct, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
