package com.authorizationapi.domain;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	
	@Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PrivilegeEnum.class)
	private List<PrivilegeEnum> privileges;
	
	
	
	public Role(String name, List<PrivilegeEnum> privileges) {
		super();
		this.name = name;
		this.privileges = privileges;
	}

	public Role() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PrivilegeEnum> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<PrivilegeEnum> privileges) {
		this.privileges = privileges;
	}
	
	
	
	

}
