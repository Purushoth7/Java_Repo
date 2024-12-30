package com.phones.configurations;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.phones.Services.JWTService;
import com.phones.Services.myuserdetailsservice;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {
	
	@Autowired
	private JWTService jwtservice;
	
	@Autowired
	private ApplicationContext context;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authheader=request.getHeader("Authorization");
		String token=null;
		String username=null;
		
		if(authheader!=null && authheader.startsWith("Bearer ")) {
			token=authheader.substring(7);
			username=jwtservice.extractusername(token);
		}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userdetails=context.getBean(myuserdetailsservice.class).loadUserByUsername(username);
			if(jwtservice.validatetoken(token, userdetails)) {
				UsernamePasswordAuthenticationToken authtoken=new UsernamePasswordAuthenticationToken(userdetails, null, userdetails.getAuthorities());
				authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authtoken);
			}
		}
		filterChain.doFilter(request, response);
	}
	
}
