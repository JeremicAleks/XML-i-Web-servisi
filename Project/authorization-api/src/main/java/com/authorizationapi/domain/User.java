package com.authorizationapi.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorColumn(name = "USER")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	
	private String salt;

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	private String name;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private Role role;

	@Column(nullable = false)
	private String lastName;
	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;
	@Column(unique = true, nullable = false)
	private String email;
	private UserStatusEnum userStatus;
	
	public UserStatusEnum getUserStatusEnum() {
		return userStatus;
	}

	public void setUserStatusEnum(UserStatusEnum userStatusEnum) {
		this.userStatus = userStatusEnum;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String name, Role role, String lastName, String username, String password, String email,String salt,UserStatusEnum u) {
		super();
		this.name = name;
		this.role = role;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.salt = salt;
		this.userStatus = u;
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
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
