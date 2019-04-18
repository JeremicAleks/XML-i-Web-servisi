package com.centralapi.domain.dto;

import javax.validation.constraints.NotNull;

import com.centralapi.domain.User;

public class UserLoginDTO {

	@NotNull(message = "Username is required!")
	private String username;
	private String name;
	private String lastName;
	private String email;
	private String adress;
	private String telephone;
	private String token;

	private String role;
	
	public UserLoginDTO() {
		super();
	}

	public UserLoginDTO(@NotNull(message = "Username is required!") String username, String name, String lastName,
			String email, String adress, String telephone, String token,
			@NotNull(message = "Authority is required!") String role) {
		super();
		this.username = username;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.adress = adress;
		this.telephone = telephone;
		this.token = token;
		this.role = role;
	}
	
	public UserLoginDTO(User user, String userToken) {
		this.username = user.getUsername();
		this.name = user.getName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.adress = user.getAdress();
		this.telephone = user.getTelephone();
		this.token = userToken;
		this.role = user.getRole().getName();
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
