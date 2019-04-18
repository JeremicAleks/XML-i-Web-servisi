package com.centralapi.domain.dto;

import javax.validation.constraints.NotNull;

public class ChangeRoleForUserDTO {
	
	@NotNull(message = "Role is required!")
	String role;
	
	@NotNull(message = "User is required!")
	String username;
	
	public ChangeRoleForUserDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	

}
