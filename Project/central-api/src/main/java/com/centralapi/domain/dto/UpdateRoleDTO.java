package com.centralapi.domain.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.centralapi.domain.xml.xml_ftn.users.PrivilegesEnum;

public class UpdateRoleDTO {

	@NotNull(message = "Role is required!")
	private String role;

	@NotNull(message = "Privileges is required!")
	private List<PrivilegesEnum> privileges;
	
	public UpdateRoleDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<PrivilegesEnum> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<PrivilegesEnum> privileges) {
		this.privileges = privileges;
	}
	
	

}
