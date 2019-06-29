package com.authorizationapi.domain.dto;

import javax.validation.constraints.NotNull;

public class TokenDTO {
	
	@NotNull(message = "Token is required!")
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

}
