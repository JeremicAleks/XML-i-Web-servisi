package com.authorizationapi.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	
	String userLogin(String username, String password);

}
