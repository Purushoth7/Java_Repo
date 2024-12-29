package com.example.country.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.country.beans.userprincipal;
import com.example.country.beans.users;
import com.example.country.respositaries.UserRepo;

@Service
public class myuserdetailsservice implements UserDetailsService {

	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		users user=repo.findByUsername(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("user not found");
		}
		return new userprincipal(user);
	}
	
	
}
