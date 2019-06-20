package com.agentapi.com.centralapi.domain.xml.xml_ftn.users;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class UserInfo {
	
    @Id
    protected String username;
   
    public UserInfo() {
		// TODO Auto-generated constructor stub
	}
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserInfo(String username, String name, String lastname, String role, String token, String email) {
		super();
		this.username = username;
		this.name = name;
		this.lastname = lastname;
		this.role = role;
		this.token = token;
		this.email = email;
	}
	
	public UserInfo(UserLoginDTO user) {
		super();
		this.username = user.getUsername();
		this.name = user.getName();
		this.lastname = user.getLastname();
		this.role = user.getRole();
		this.token = user.getToken();
		this.email = user.getEmail();
	}

	protected String name;
    
    protected String lastname;
    
    protected String role;
    
    protected String token;
   
    protected String email;

}
