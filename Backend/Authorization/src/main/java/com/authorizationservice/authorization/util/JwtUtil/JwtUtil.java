package com.authorizationservice.authorization.util.JwtUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtUtil {
	private static final String SECRET_KEY = "abcABC09";
	
	private long currentTime;
	
	private long expirationTime;
	
	public long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}

	public long getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(long expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String extractUsername(String token) {
		log.info("BEGIN - [extractUserName(tokenn)]");
		log.info("END - [extractUserName(tokenn)]");
		return extractClaim(token,Claims::getSubject);
		
	}
	
	public Date extractExpiration(String token) {
		log.info("BEGIN - [extractExpiration(tokenn)]");
		log.info("END - [extractEndExpiraation(tokenn)]");
		return extractClaim(token,Claims::getExpiration);
		
	}
	
	public <T> T extractClaim(String token,Function<Claims,T> claimsResolver) {
		log.info("BEGIN - [extractClaim(tokenn)]");
		final Claims claims=extractAllClaims(token);
		log.info("END- [extractClaim(tokenn)]");
		
		return claimsResolver.apply(claims);
		
	}
	
	public Claims extractAllClaims(String token) {
		log.info("BEGIN - [extractAllCliams(tokenn)]");
		log.info("END- [extractAllClaim(tokenn)]");
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	
	}
	
	private boolean isTokenExpired(String token) {
		log.info("BEGIN - [CheckTokenExpired(tokenn)]");
		log.info("END- [end check token expired(tokenn)]");
		return extractExpiration(token).before(new Date());
		
	}
	
	public String generateToken(UserDetails userDetails) {
		log.info("BEGIN - [genrateToken(userDetails)]");
		log.info("END- [genrateToken(userDetails)]");
	    Map<String,Object> claimsMap=new HashMap<>();
		
		return createToken(claimsMap,userDetails.getUsername());
	
	}
	
	public String createToken(Map<String,Object> claims,String subject) {
		log.info("BEGIN - [createToken(tokenn)]");
		log.info("END- [createToken(tokenn)]");
		
		setCurrentTime(System.currentTimeMillis());
		setExpirationTime(getCurrentTime()+1000*60*30);
		
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(getExpirationTime()))
				.signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
		
	}
	
	public Boolean validateToken(String token,UserDetails userDetails){
		log.info("BEGIN - [ValidateToken(tokenn)]");
		
		final String usernameString=extractUsername(token);
		log.debug("username"+usernameString);
		log.info("END- [ValidateToken(tokenn)]");
		return (usernameString.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	

}
