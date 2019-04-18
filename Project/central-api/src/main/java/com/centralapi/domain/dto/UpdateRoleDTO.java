package com.centralapi.domain.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.centralapi.domain.PrivilegeEnum;

public class UpdateRoleDTO {

	@NotNull(message = "Role is required!")
	private String role;

	@NotNull(message = "Privileges is required!")
	private List<PrivilegeEnum> privileges;
	
	public UpdateRoleDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<PrivilegeEnum> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<PrivilegeEnum> privileges) {
		this.privileges = privileges;
	}
	
	

}
