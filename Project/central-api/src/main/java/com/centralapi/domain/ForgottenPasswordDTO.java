package com.centralapi.domain;

import javax.validation.constraints.NotNull;

public class ForgottenPasswordDTO {
	
	@NotNull(message = "Username is required")
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
