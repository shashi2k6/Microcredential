package com.login.jwtutil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTUtil {

	private String SECRET_KEY = "Secret";
	
	public String generateToken(UserDetails userDetails) {
		Map<String,Object> claims = new HashMap<>();
		return createTokens(claims,userDetails.getUsername());
	}

	public String createTokens(Map<String, Object> claims, String username) {
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis()+10000*60*60*10)).signWith(SignatureAlgorithm.HS256, 
				SECRET_KEY).compact();
	}
	
	public Map<String, String> createToken( String username) {
		String jwtToken =  Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis()+10000*60*60*10)).signWith(SignatureAlgorithm.HS256, 
				SECRET_KEY).compact();
		Map<String,String> map=new HashMap<>();
		map.put("token", jwtToken);
		return map;
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
		return (userName.equals(userDetails.getUsername() ) && !isTokenExpired(token));
	}
	
	public String isTokenValid(String token) {
		final String userName = extractUserName(token);
		System.out.println(userName);
		if( !isTokenExpired(token)){
			return "valid";
		}else {
			return "Not Valid";
		}
	}

	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public Date  extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims,T> object) {
		final  Claims claim = exrtractAllClaim(token);
		return object.apply(claim);
	}

	public Claims exrtractAllClaim(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
}
 