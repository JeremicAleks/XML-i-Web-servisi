package com.authorizationapi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Privileges {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	
	public Privileges() {
		// TODO Auto-generated constructor stub
	}
	
	public Privileges(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
