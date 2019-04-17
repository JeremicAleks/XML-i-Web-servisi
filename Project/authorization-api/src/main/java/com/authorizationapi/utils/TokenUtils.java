package com.authorizationapi.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.authorizationapi.domain.BlackListToken;
import com.authorizationapi.domain.SecurityUser;
import com.authorizationapi.domain.User;
import com.authorizationapi.repo.BlackListTokenRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtils {

	@Value("${token.secret}")
	private String secret;

	@Value("${token.expiration}")
	private Long expiration;

	@Autowired
	private BlackListTokenRepository blackRepo;

	private Date generateCurrentDate() {

		return new Date(System.currentTimeMillis());

	}

	@Scheduled(cron = "0 0/30 * * * *")
	public void filterBlackList() {
		List<BlackListToken> blackList = blackRepo.getExp(new Date());
		blackRepo.deleteAll(blackList);
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
		String role = "";
		for (GrantedAuthority g : userDetails.getAuthorities()) {
			role += g;
		}
		claims.put("role", role);
		claims.put("created", this.generateCurrentDate());
		claims.put("iat", new UUID(10, 10));

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
	
	private Boolean notInBlackList(String token) {
		
		BlackListToken b = blackRepo.findByToken(token);
		if(b==null) {
			return true;
		}
		return false;
		
	}
	
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
	

	public Boolean validateToken(String token, UserDetails userDetails) {
		SecurityUser user = (SecurityUser) userDetails;
		final String username = this.getUsernameFromToken(token);
		return (username.equals(user.getUsername()) && !(this.isTokenExpired(token) && notInBlackList(token)));
	}
	
	public Boolean validateToken(String token, User user) {
		final String username = this.getUsernameFromToken(token);
		return (username.equals(user.getUsername()) && !(this.isTokenExpired(token) && notInBlackList(token)));
	}
	

}
