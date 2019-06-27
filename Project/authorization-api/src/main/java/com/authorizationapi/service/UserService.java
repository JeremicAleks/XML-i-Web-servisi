package com.authorizationapi.service;

import org.springframework.stereotype.Service;

import com.authorizationapi.domain.dto.ChangePasswordDTO;
import com.authorizationapi.domain.dto.RegisterUserDTO;
import com.authorizationapi.domain.dto.UserLoginDTO;

@Service
public interface UserService {
	
	UserLoginDTO userLogin(String username, String password);
	Object userRegister(RegisterUserDTO user);
	Boolean changePassword(ChangePasswordDTO change);

}
