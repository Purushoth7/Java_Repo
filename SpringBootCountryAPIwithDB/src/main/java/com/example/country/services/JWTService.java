package com.example.country.services;

import java.security.Key;
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
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	private String secretkey;

	public String generatetoken(String username) throws InvalidKeyException, NoSuchAlgorithmException {
		Map<String, Object> claims=new HashMap<String, Object>();
		return Jwts.builder().claims().add(claims).subject(username).issuedAt(new Date(System.currentTimeMillis()))
					.expiration(new Date(System.currentTimeMillis() +30*1000)).and().signWith(getKey()).compact();
	}

	private JWTService() throws NoSuchAlgorithmException {
		KeyGenerator keygen=KeyGenerator.getInstance("HmacSHA256");
		SecretKey sk=keygen.generateKey();
		secretkey=Base64.getEncoder().encodeToString(sk.getEncoded());
	}
	
	private SecretKey getKey() {
		byte[] keybytes=Decoders.BASE64.decode(secretkey);
		return Keys.hmacShaKeyFor(keybytes);
	}
	
	private Claims extractallclaims(String token) throws JwtException, IllegalArgumentException, NoSuchAlgorithmException {
		return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
	}
	
	private<T> T extractclaim(String token, Function<Claims, T>claimresolver) throws JwtException, IllegalArgumentException, NoSuchAlgorithmException {
		final Claims claims=extractallclaims(token);
		return claimresolver.apply(claims);
	}

	public String extractusername(String token) throws JwtException, IllegalArgumentException, NoSuchAlgorithmException {
		return extractclaim(token, Claims::getSubject);
	}

	private Date extractexpiration(String token) throws JwtException, IllegalArgumentException, NoSuchAlgorithmException {
		return extractclaim(token, Claims::getExpiration);
	}

	private boolean isTokenExpired(String token) throws JwtException, IllegalArgumentException, NoSuchAlgorithmException {
		return extractexpiration(token).before(new Date());
	}
	
	public boolean validatetoken(String token, UserDetails userdetails) throws JwtException, IllegalArgumentException, NoSuchAlgorithmException {
		final String userName=extractusername(token);
		return (userName.equals(userdetails.getUsername()) && !isTokenExpired(token));
	}

}
