package com.authorizationapi.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtils {

	@Value("${token.secret}")
	private String secret;

	@Value("${token.expiration}")
	private Long expiration;
	
	private Date generateCurrentDate() {

		return new Date(System.currentTimeMillis());

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



	private Claims getClaimsFromToken(String token) {

		Claims claims;

		try {

			claims = Jwts.parser().setSigningKey(this.secret.getBytes("UTF-8")).parseClaimsJws(token).getBody();

		} catch (Exception e) {

			claims = null;

		}

		return claims;

	}


	private Date generateExpirationDate() {

		return new Date(System.currentTimeMillis() + this.expiration * 1000);

	}



	public String generateToken(UserDetails userDetails) {

		Map<String, Object> claims = new HashMap<String, Object>();
		
		claims.put("sub", userDetails.getUsername());
		String role="";
		for (GrantedAuthority g : userDetails.getAuthorities()) {
			role+=g;
		}
		claims.put("role", role);
		claims.put("created", this.generateCurrentDate());

		return this.generateToken(claims);

	}



	private String generateToken(Map<String, Object> claims) {

		try {

			return Jwts.builder().setClaims(claims).setExpiration(this.generateExpirationDate())

					.signWith(SignatureAlgorithm.HS512, this.secret.getBytes("UTF-8")).compact();

		} catch (UnsupportedEncodingException ex) {


			return Jwts.builder().setClaims(claims).setExpiration(this.generateExpirationDate())

					.signWith(SignatureAlgorithm.HS256, this.secret).compact();

		}

	}
	
	private Boolean isTokenExpired(String token) {

		final Date expiration = this.getExpirationDateFromToken(token);

		return expiration.before(this.generateCurrentDate());

	}



	public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {

		return (!(this.isTokenExpired(token)));

	}



	public String refreshToken(String token) {

		String refreshedToken;

		try {

			final Claims claims = this.getClaimsFromToken(token);

			claims.put("created", this.generateCurrentDate());

			refreshedToken = this.generateToken(claims);

		} catch (Exception e) {

			refreshedToken = null;

		}

		return refreshedToken;

	}

}
