package com.authorizationapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authorizationapi.domain.SecurityUser;
import com.authorizationapi.domain.User;
import com.authorizationapi.exception.UserCreditalsException;
import com.authorizationapi.repo.UserRepository;
import com.authorizationapi.utils.TokenUtils;

@Service
public class UserServiceCon implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcript;

	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private UserDetailsServiceCon userDetailService;

	
	public User loadUserByUsername(String username) throws UserCreditalsException {

		User user = (User) userRepository.findByUsername(username);

		if (user == null) {

			throw new UserCreditalsException("Username '" + username + "' doesn't exist!");

		} else {

			return user;

		}

	}

	@Override
	public String userLogin(String username, String password) {

		User user = loadUserByUsername(username);

		if (bcript.matches(password, user.getPassword())) {

			SecurityUser userDetails = (SecurityUser) this.userDetailService.loadUserByUsername(username);

			String token = this.tokenUtils.generateToken(userDetails);
			
			return token;
		}
			throw new UserCreditalsException("Wrong password");

		
	}
}
