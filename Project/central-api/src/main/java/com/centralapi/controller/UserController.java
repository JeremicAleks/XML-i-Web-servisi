package com.centralapi.controller;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centralapi.domain.User;
import com.centralapi.domain.dto.UserLoginDTO;
import com.centralapi.repo.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {

	  private static final Logger logger = Logger.getLogger(UserController.class);
	  
	
	@Autowired
	UserRepository userRepo;

	@GetMapping(value = "/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUsers() throws UnknownHostException {
		List<User> users = userRepo.findAll();
		List<UserLoginDTO> userdto = new ArrayList<>();
		for (User user : users) {
			userdto.add(new UserLoginDTO(user.getUsername(), user.getName(), user.getLastName(), user.getEmail(),
					user.getAdress(), user.getTelephone(), "", user.getRole().getName()));
		}
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUser(){

		return new ResponseEntity<>(null, HttpStatus.OK);
	}


}
