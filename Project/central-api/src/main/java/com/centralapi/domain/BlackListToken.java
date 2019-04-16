package com.centralapi.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="black_list")
public class BlackListToken {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String token;
	private Date exp;
	

	public BlackListToken() {
		// TODO Auto-generated constructor stub
	}
	
	public BlackListToken(String token,Date exp) {
		super();
		this.token = token;
		this.exp=exp;
	}
	
	public Date getExp() {
		return exp;
	}

	public void setExp(Date exp) {
		this.exp = exp;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	
}
