package com.authorizationapi.converter;

import com.authorizationapi.domain.RegistredUser;
import com.authorizationapi.domain.dto.RegisterUserDTO;

public class RegUserDAOtoRegUser {

	/**
	 * Converter(Factory) koja od DTO objekta pravi registrovanog korisnika
	 * 
	 * @param user
	 * @return RegisterUser
	 */

	public static RegistredUser create(RegisterUserDTO user) {

		return new RegistredUser(user.getName(), user.getLastname(), user.getUsername(), user.getPassword(),
				user.getEmail(), null, null);
	}

}
