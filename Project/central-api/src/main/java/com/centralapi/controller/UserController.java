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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centralapi.domain.dto.UserLoginDTO;
import com.centralapi.domain.xml.xml_ftn.users.User;
import com.centralapi.service.UserService;


@RestController
@RequestMapping("/api/user")
public class UserController {

	  private static final Logger logger = Logger.getLogger(UserController.class);
	  
	@Autowired
	UserService userService;

	@GetMapping(value = "/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUsers() throws UnknownHostException {
		List<User> users = userService.findAll();
		List<UserLoginDTO> userdto = new ArrayList<>();
		for (User user : users) {
			userdto.add(new UserLoginDTO(user.getUsername(), user.getName(), user.getLastName(), user.getEmail(),
					 "", user.getRole().getName()));
		}
		return new ResponseEntity<>(userdto, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUser(@PathVariable Long id){
		User user = userService.findById(id);

		if(user==null)
			return new ResponseEntity<>("User not found!",HttpStatus.OK);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}


}
