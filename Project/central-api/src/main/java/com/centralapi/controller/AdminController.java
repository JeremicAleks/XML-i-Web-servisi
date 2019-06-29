package com.centralapi.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centralapi.domain.xml.xml_ftn.users.AgentDTO;
import com.centralapi.exception.ResponseMessage;
import com.centralapi.service.UserService;

@RestController
@RequestMapping("api/admin")
public class AdminController {

	public static Logger logger = Logger.getLogger(UserService.class);

	@Autowired
	UserService userService;

	@GetMapping(value = "/comment/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllComments() {

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PostMapping(value = "/comment/{id}")
	public ResponseEntity<?> publishComment() {

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PutMapping(value = "/user/block")
	public ResponseEntity<?> blockUser(@RequestBody String username) {

		System.out.println("username: " + username);
		ResponseMessage response = userService.blockUser(username);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(value = "/user/activate")
	public ResponseEntity<?> activateUser(@RequestBody String username) {

		System.out.println("username: " + username);
		ResponseMessage response = userService.activateUser(username);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/user/delete/{id}")
	public ResponseEntity<?> deleteUser() {

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PostMapping(value = "agent/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addAgent(@RequestBody AgentDTO newAgent) {

		logger.debug("Admin user tries to add new agent!");
		System.out.println("uspeoo");

		ResponseMessage responseMessage = userService.promoteAgent(newAgent);

		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}

}
