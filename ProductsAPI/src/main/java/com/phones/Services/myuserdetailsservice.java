package com.phones.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.phones.Models.userprincipal;
import com.phones.Models.users;
import com.phones.Repositories.UserRepo;

@Service
public class myuserdetailsservice implements UserDetailsService {
	
	@Autowired
	private UserRepo userrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		users user=userrepo.findByUsername(username);
		
		if(user==null)
			throw new UsernameNotFoundException("user not found");
		
		return new userprincipal(user);
	}
	
}
