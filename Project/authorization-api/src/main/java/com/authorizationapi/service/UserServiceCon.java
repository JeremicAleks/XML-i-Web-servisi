package com.authorizationapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authorizationapi.converter.RegUserDAOtoRegUser;
import com.authorizationapi.domain.RegistredUser;
import com.authorizationapi.domain.SecurityUser;
import com.authorizationapi.domain.User;
import com.authorizationapi.domain.dto.RegisterUserDTO;
import com.authorizationapi.domain.dto.UserLoginDTO;
import com.authorizationapi.exception.ResponseMessage;
import com.authorizationapi.exception.UserCreditalsException;
import com.authorizationapi.repo.UserRepository;
import com.authorizationapi.utils.PasswordValidation;
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
	public UserLoginDTO userLogin(String username, String password) {

		User user = loadUserByUsername(username);

		if (bcript.matches(password, user.getPassword())) {

			SecurityUser userDetails = (SecurityUser) this.userDetailService.loadUserByUsername(username);

			String token = this.tokenUtils.generateToken(userDetails);

			return new UserLoginDTO(user, token);
		}
		throw new UserCreditalsException("Wrong password");

	}

	@Override
	public Object userRegister(RegisterUserDTO user) {

		User userTemp = userRepository.findByUsername(user.getUsername());
		if (userTemp != null)
			throw new UserCreditalsException("Username '" + userTemp.getUsername() + "' already exist!");

		if (!PasswordValidation.validPassword(user.getPassword(), user.getRePassword()))
			throw new UserCreditalsException("Passwords don't match!");

		user.setPassword(bcript.encode(user.getRePassword()));
		RegistredUser regUser = RegUserDAOtoRegUser.create(user);
		userRepository.save(regUser);

		return new ResponseMessage("Successfull registration!");
	}
}
