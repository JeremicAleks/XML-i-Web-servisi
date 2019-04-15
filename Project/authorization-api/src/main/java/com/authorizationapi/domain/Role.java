package com.authorizationapi.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private RoleEnum name;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Privileges> privileges;
	
	public Role() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Role(RoleEnum name, List<Privileges> privileges) {
		super();
		this.name = name;
		this.privileges = privileges;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public RoleEnum getName() {
		return name;
	}
	public void setName(RoleEnum name) {
		this.name = name;
	}
	public List<Privileges> getPrivilages() {
		return privileges;
	}
	public void setPrivilages(List<Privileges> privilages) {
		this.privileges = privilages;
	}
	
	

}
