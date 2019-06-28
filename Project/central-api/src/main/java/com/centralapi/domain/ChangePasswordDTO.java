package com.centralapi.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ChangePasswordDTO {

	@NotNull(message = "Password is required")
	@Size(min = 4, message = "Password must be at least 4 character long")
	String password;
	@NotNull(message = "Re-Password is required")
	@Size(min = 4, message = "Re-Password must be at least 4 character long")
	String rePassword;
	@NotNull(message = "Password is required")
	String username;
	@NotNull(message = "Token is required")
	String token;
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
