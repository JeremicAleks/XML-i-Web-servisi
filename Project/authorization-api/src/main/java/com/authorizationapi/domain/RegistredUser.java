package com.authorizationapi.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("REGUSER")
public class RegistredUser extends User {
	
	public RegistredUser() {
		// TODO Auto-generated constructor stub
	}

	public RegistredUser(String name, String lastname, String username, String password, String email, Object object,
			Object object2) {
		super(name,null,lastname,username,password,email,null,null);
	}

}
