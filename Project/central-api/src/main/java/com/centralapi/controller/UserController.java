package com.centralapi.controller;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.centralapi.domain.ChangePasswordDTO;
import com.centralapi.domain.ForgottenPasswordDTO;
import com.centralapi.domain.TokenDTO;
import com.centralapi.domain.xml.xml_ftn.users.User;
import com.centralapi.domain.xml.xml_ftn.users.UserLoginDTO;
import com.centralapi.service.UserService;


@RestController
@RequestMapping("/api/user")
public class UserController {

	  private static final Logger logger = Logger.getLogger(UserController.class);
	  
	@Autowired
	UserService userService;
	
	@Autowired
	RestTemplate restTemplate;

	@GetMapping(value = "/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUsers() throws UnknownHostException {
		List<User> users = userService.findAll();
		List<UserLoginDTO> userdto = new ArrayList<>();
		for (User user : users) {
			userdto.add(new UserLoginDTO(user.getUsername(), user.getName(), user.getLastName(),
					  user.getRole().getName(),"",user.getEmail()));
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
	
	@RequestMapping(method = RequestMethod.POST, value = "/forgottenPassword")
	public ResponseEntity<?> forgottenPassword(@Valid @RequestBody ForgottenPasswordDTO user) {
		
		Object answer = restTemplate.postForObject("http://autorization-api/api/forgottenPassword",user, Object.class);

		return (ResponseEntity) answer;
	
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/verifyForgottenPasswordToken")
	public ResponseEntity<?> forgottenPassword(@Valid @RequestBody TokenDTO token) {

		
		
		Object answer = restTemplate.postForObject("http://autorization-api/api/verifyForgottenPasswordToken",token, Object.class);

		return (ResponseEntity) answer;
	
		
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/changePassword")
	public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO changePassword) {

		
		Object answer = restTemplate.postForObject("http://autorization-api/api/changePassword",changePassword, Object.class);

		return (ResponseEntity) answer;
	}
	
	


}
