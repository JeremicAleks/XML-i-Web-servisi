package com.authorizationapi.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.authorizationapi.domain.BlackListToken;
import com.authorizationapi.domain.dto.LoginDTO;
import com.authorizationapi.domain.dto.RegisterUserDTO;
import com.authorizationapi.domain.dto.UserLoginDTO;
import com.authorizationapi.repo.BlackListTokenRepository;
import com.authorizationapi.repo.UserRepository;
import com.authorizationapi.service.UserService;
import com.authorizationapi.utils.TokenUtils;

@Controller
@CrossOrigin
@RequestMapping("/api")
public class TokenController {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepo;

	@Autowired
	BlackListTokenRepository blackList;

	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	BCryptPasswordEncoder bcript;

	@RequestMapping(method = RequestMethod.POST, value = "/token", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loginUser(@Validated @RequestBody LoginDTO user) {

		UserLoginDTO userLoginDTO = userService.userLogin(user.getUsername(), user.getPassword());
		
		if (userLoginDTO != null) {
			return new ResponseEntity<Object>(userLoginDTO, HttpStatus.OK);
		}
		
		return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDTO user) {

		return new ResponseEntity<>(userService.userRegister(user),HttpStatus.OK);

	}
	@RequestMapping(method = RequestMethod.GET, value = "/revoke")
	public ResponseEntity<?> revokeToken(@RequestHeader("Token-Authority") String token) {

		Date date = tokenUtils.getExpirationDateFromToken(token);
		if (blackList.findByToken(token) != null || date != null) {
			blackList.save(new BlackListToken(token, date));
		}
		return new ResponseEntity<String>("Successfully logout!", HttpStatus.OK);

	}
}
