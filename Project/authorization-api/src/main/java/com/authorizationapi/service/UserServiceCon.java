package com.authorizationapi.service;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authorizationapi.converter.RegUserDAOtoRegUser;
import com.authorizationapi.domain.RegistredUser;
import com.authorizationapi.domain.Role;
import com.authorizationapi.domain.SecurityUser;
import com.authorizationapi.domain.User;
import com.authorizationapi.domain.dto.ChangePasswordDTO;
import com.authorizationapi.domain.dto.RegisterUserDTO;
import com.authorizationapi.domain.dto.UserLoginDTO;
import com.authorizationapi.exception.ResponseMessage;
import com.authorizationapi.exception.UserCreditalsException;
import com.authorizationapi.repo.RegUserRepository;
import com.authorizationapi.repo.RoleRepository;
import com.authorizationapi.repo.UserRepository;
import com.authorizationapi.utils.PasswordValidation;
import com.authorizationapi.utils.TokenUtils;

@Service
public class UserServiceCon implements UserService {
	
	public static final Logger logger = Logger.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcript;

	@Autowired
	private TokenUtils tokenUtils;

	@Value("${security.pepper}")
	private String pepper;

	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	RegUserRepository regRepo;
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	private UserDetailsServiceCon userDetailService;

	public User loadUserByUsername(String username) throws UserCreditalsException {

		User user = (User) userRepository.findByUsername(username);
		if (user == null) {
			logger.warn("Username '" + username + "' doesn't exist!");
			throw new UserCreditalsException("Username '" + username + "' doesn't exist!");
		} else {
			logger.debug("Loaded user from database! Username: "+username);
			return user;
		}

	}

	@Override
	public UserLoginDTO userLogin(String username, String password) {

		System.out.println("Da li udjes ovde");
		User user = loadUserByUsername(username);
		String fullPass = password + user.getSalt() + pepper;

		if (bcript.matches(fullPass, user.getPassword())) {

			SecurityUser userDetails = (SecurityUser) this.userDetailService.loadUserByUsername(username);

			String token = this.tokenUtils.generateToken(userDetails);
			MDC.put("login","success");
			MDC.put("user",username);
			logger.info("Successfully logged in!");
			return new UserLoginDTO(user, token);
		}
		MDC.put("login","failure");
		MDC.put("user",username);
		logger.warn("Invalid login!");
		throw new UserCreditalsException("Wrong password!");

	}

	@Override
	public Object userRegister(RegisterUserDTO user) {

		User userTemp = userRepository.findByUsername(user.getUsername());
		if (userTemp != null) {

			MDC.put("user",user.getUsername());
			logger.info("Username '" + userTemp.getUsername() + "' already exist!");
			throw new UserCreditalsException("Username '" + userTemp.getUsername() + "' already exist!");

		}
		if (!PasswordValidation.validPassword(user.getPassword(), user.getRePassword())) {
			
			MDC.put("user",user.getUsername());
			logger.info("Passwords don't match!");

			throw new UserCreditalsException("Passwords don't match!");
		
		}
		String salt = KeyGenerators.secureRandom().toString();
		String fullPass = user.getRePassword() + salt + pepper;
		user.setPassword(bcript.encode(fullPass));
		logger.debug("Encode password for user using salt and pepper..");
		RegistredUser regUser = RegUserDAOtoRegUser.create(user);
		logger.debug("Creating registred user from DTO..");
		regUser.setSalt(salt);
		Role r = roleRepo.findByName("DefaultRole");
		regUser.setRole(r);
		logger.debug("Set role for user..");
		regRepo.saveAndFlush(regUser);

		MDC.put("user",user.getUsername());
		logger.info("Successfull registration!");
		return new ResponseMessage("Successfull registration!");
	}
	
	@Override
	public Boolean changePassword(ChangePasswordDTO change) {
		
		User userTemp = userRepository.findByUsername(change.getUsername());
		if (userTemp == null) {

			MDC.put("user",change.getUsername());
			logger.info("Username '" + userTemp.getUsername() + "' doesnt exist!");
			throw new UserCreditalsException("Username '" + userTemp.getUsername() + "' doesnt exist!");

		}
		if (!PasswordValidation.validPassword(change.getPassword(), change.getRePassword())) {
			
			MDC.put("user",change.getUsername());
			logger.info("Passwords don't match!");

			throw new UserCreditalsException("Passwords don't match!");
		
		}
		
		String salt = KeyGenerators.secureRandom().generateKey().toString();
		String fullPass = change.getRePassword() + salt + pepper;
		userTemp.setPassword(bcript.encode(fullPass));
		logger.debug("Encode password for user using salt and pepper..");
		userTemp.setSalt(salt);
		userRepo.saveAndFlush(userTemp);

		MDC.put("user",userTemp.getUsername());
		logger.info("Successfully changed password!");
		return true;
		
		
		
	}
}
