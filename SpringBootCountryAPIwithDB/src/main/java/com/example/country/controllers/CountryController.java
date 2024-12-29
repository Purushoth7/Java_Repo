package com.example.country.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.country.beans.Country;
import com.example.country.beans.users;
import com.example.country.services.CountryService;

import io.jsonwebtoken.security.InvalidKeyException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class CountryController {

	@Autowired
	CountryService countryserv;
	
	private String India="democratic";
	
	@PostMapping("/login")
	public String login(@RequestBody users user) throws InvalidKeyException, NoSuchAlgorithmException {
		return countryserv.verifyuser(user);
	}
	
	//getting all countries using get method
	@GetMapping("/getallcountries")
	public List<Country> getcountries(HttpServletRequest request) {
		return countryserv.getAllCountries();
		//getting session id
		//return request.getSession().getId();
		//getting csrf token
		//return (CsrfToken) request.getAttribute("_csrf");
	}
	
	//getting country by id using get method
	@GetMapping("/getcountry/{id}")
	public ResponseEntity<Country> getbyID(@PathVariable(value="id")int id) {
		try
		{
			Country country=countryserv.getbyID(id);
			return new ResponseEntity<Country>(country, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//getting country by name using get method
	@GetMapping("/getcountry/countryname")
	public ResponseEntity<Country> getbyName(@RequestParam(value="name")String Cname) {
		try
		{
			Country country=countryserv.getbyName(Cname);
			return new ResponseEntity<Country>(country, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//adding country using post method
	@PostMapping("/addcountry")
	public ResponseEntity<Country> addcountry(@RequestBody Country country) {
		try
		{
			countryserv.addCountry(country);
			return new ResponseEntity<Country>(country, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/register")
	public users adduser(@RequestBody users user) {
		return countryserv.adduser(user);
	}
	
	//updating country using put method
	@PutMapping("/updatecountry/{id}")
	public ResponseEntity<Country> updCountry(@PathVariable(value="id")int id, @RequestBody Country country) {
		try
		{
			Country existcountry=countryserv.getbyID(id);
			existcountry.setCname(country.getCname());
			existcountry.setCapital(country.getCapital());
			Country updcountry=countryserv.updCountry(existcountry);
			return new ResponseEntity<Country>(updcountry, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	//deleting country using delete method
	@DeleteMapping("/deletecountry/{id}")
	public String delCountry(@PathVariable(value="id")int id) {
		return countryserv.delCountry(id);
	}
	
}
