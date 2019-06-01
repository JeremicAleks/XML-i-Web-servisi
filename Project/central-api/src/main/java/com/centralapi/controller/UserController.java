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
		for(int i =0 ; i<500;i++) {  
		logger.info("LEEF:1.0|Microsoft|MSExchange|4.0 SP1|15345|" + 
				"src=7.5.6.6	dst=172.50.123.1	sev=5	cat=anomaly	srcPort=81	dstPort=21	" + 
				"usrName=joe.black");
		List<User> users = userRepo.findAll();
		List<UserLoginDTO> userdto = new ArrayList<>();
		for (User user : users) {
			userdto.add(new UserLoginDTO(user.getUsername(), user.getName(), user.getLastName(), user.getEmail(),
					user.getAdress(), user.getTelephone(), "", user.getRole().getName()));
		}
		
	}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
