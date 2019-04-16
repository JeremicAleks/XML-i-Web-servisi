package com.centralapi.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.centralapi.domain.BlackListToken;
import com.centralapi.domain.SecurityUser;
import com.centralapi.repo.BlackListTokenRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;

@Component
public class TokenUtils {
	

	@Value("${token.secret}")
	private String secret;

	@Value("${token.expiration}")
	private Long expiration;
	
	@Autowired
	private BlackListTokenRepository blackListRepo;
	
	public String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	public Date getCreatedDateFromToken(String token) {
		Date created;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			created = new Date((Long) claims.get("created"));
		} catch (Exception e) {
			created = null;
		}
		return created;
	}
	
	public String getRole(String token) {
		String created;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			created = claims.get("role").toString();
		} catch (Exception e) {
			created = null;
		}
		return created;
	}
	
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		Header header;
		try {
			claims = Jwts.parser().setSigningKey(this.secret.getBytes("UTF-8")).parseClaimsJws(token).getBody();
			header = Jwts.parser().setSigningKey(this.secret.getBytes("UTF-8")).parseClaimsJws(token).getHeader();
			if(header==null || header.get("alg").equals("none")) {
				claims = null;
			}
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}
	
	private Date generateCurrentDate() {
		return new Date(System.currentTimeMillis());
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = this.getExpirationDateFromToken(token);
		return expiration.before(this.generateCurrentDate());
	}
	
	private Boolean notInBlackList(String token) {
		
		BlackListToken b = blackListRepo.findByToken(token);
		if(b==null) {
			return true;
		}
		return false;
		
	}
	

	public Boolean validateToken(String token, UserDetails userDetails) {
		SecurityUser user = (SecurityUser) userDetails;
		final String username = this.getUsernameFromToken(token);
		return (username.equals(user.getUsername()) && !(this.isTokenExpired(token) && notInBlackList(token)));
	}

}
