package com.authorizationapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.authorizationapi.domain.Role;
import com.authorizationapi.domain.RoleEnum;
import com.authorizationapi.domain.dto.LoginDTO;
import com.authorizationapi.repo.UserRepository;
import com.authorizationapi.service.UserService;

@Controller
@CrossOrigin
@RequestMapping("/api")
public class TokenController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BCryptPasswordEncoder bcript;

	@RequestMapping(method = RequestMethod.POST, value = "/token", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loginUser(@Validated @RequestBody LoginDTO user) {

		System.out.println("Dsad");
		String token = userService.userLogin(user.getUsername(), user.getPassword());

		return new ResponseEntity<String>(token, HttpStatus.OK);

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/user")
	public ResponseEntity<?> addUser() {
		
		userRepo.save(new com.authorizationapi.domain.User("Maki", new Role(RoleEnum.SECADMIN,null), "dsa", "maki", bcript.encode("maki"), "dsa", "421", "421"));

		return new ResponseEntity<String>("Cool", HttpStatus.OK);

	}
}
