package com.example.country.services;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.country.beans.Country;
import com.example.country.beans.users;
import com.example.country.respositaries.CountryRepository;
import com.example.country.respositaries.UserRepo;

import io.jsonwebtoken.security.InvalidKeyException;

@Component
@Service
public class CountryService {
	
	//autowiring jpa methods
	
	private int countries=195;
	
	@Autowired
	private CountryRepository countryrep;
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private JWTService jwts;
	
	@Autowired
	private AuthenticationManager authmanager;
	
	@Autowired
	private PasswordEncoder passencoder;
	
	public CountryService(PasswordEncoder passencoder) {
		super();
		this.passencoder = passencoder;
	}

	//getting all countries
	public List<Country> getAllCountries() {
		return countryrep.findAll();
	}
	
	//getting country by id
	public Country getbyID(int id) {
		return countryrep.findById(id).get();
	}
	
	//getting country by name
	public Country getbyName(String Cname) {
		List<Country> countries=countryrep.findAll();
		Country country=null;
		
		for(Country con:countries) {
			if(con.getCname().equalsIgnoreCase(Cname)) {
				country=con;
			}
		}
		return country;
	}
	
	//utility method to generate id
	public int getmaxID() {
		return countryrep.findAll().size()+1;
	}
	
	//adding country
	public Country addCountry(Country country) {
		country.setId(getmaxID());
		countryrep.save(country);
		return country;
	}
	
	public users adduser(users user) {
		user.setPassword(passencoder.encode(user.getPassword()));
		return repo.save(user);
	}
	
	public String verifyuser(users user) throws InvalidKeyException, NoSuchAlgorithmException {
		Authentication auth=authmanager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if(auth.isAuthenticated()) {
			return jwts.generatetoken(user.getUsername());
		}
		return "failed";
	}
	
	//updating country
	public Country updCountry(Country country) {
		countryrep.save(country);
		return country;
	}
	
	//deleting country
	public String delCountry(int id) {
		Country country=countryrep.findById(id).get();
		String countryname=country.getCname();
		countryrep.deleteById(id);
		return id+" "+" "+countryname+" "+"has been deleted!";
	}
	
}
