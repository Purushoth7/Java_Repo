package com.phones.Services;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	private String secretkey;

	public JWTService() {
		try {
			KeyGenerator keygen=KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk=keygen.generateKey();
			secretkey=Base64.getEncoder().encodeToString(sk.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	
	private SecretKey getkey() {
		byte[] keybytes=Decoders.BASE64.decode(secretkey);
		return Keys.hmacShaKeyFor(keybytes);
	}

	public String generatetoken(String username) {
		Map<String, Object> claims=new HashMap<String, Object>();
		return Jwts
				.builder()
				.claims()
				.add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() +1000*30))
				.and()
				.signWith(getkey())
				.compact();
	}
	
	private Claims extractallclaims(String token) {
		return Jwts.parser().verifyWith(getkey()).build().parseSignedClaims(token).getPayload();
	}
	
	private<T> T extractclaim(String token, Function<Claims, T>claimresolver) {
		final Claims claims=extractallclaims(token);
		return claimresolver.apply(claims);
	}

	public String extractusername(String token) {
		return extractclaim(token, Claims::getSubject);
	}

	private Date extractexpiration(String token) {
		return extractclaim(token, Claims::getExpiration);
	}

	private boolean isTokenExpired(String token) {
		return extractexpiration(token).before(new Date());
	}
	
	public boolean validatetoken(String token, UserDetails userdetails) {
		final String userName=extractusername(token);
		return (userName.equals(userdetails.getUsername()) && !isTokenExpired(token));
	}

}
