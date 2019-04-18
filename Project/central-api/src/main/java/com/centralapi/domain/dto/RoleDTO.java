package com.centralapi.domain.dto;

import javax.validation.constraints.NotNull;

public class RoleDTO {
	
	@NotNull(message = "Role is required!")
	String role;
	
	public RoleDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
}
