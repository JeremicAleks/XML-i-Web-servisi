package com.centralapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centralapi.domain.User;
import com.centralapi.repo.UserRepository;

@RestController
@RequestMapping("/api/public")
public class TestUserController {
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findCertificates() {
		
		userRepo.deleteAll();
        return new ResponseEntity<>("Ima ih" + userRepo.count(), HttpStatus.OK);
    }

}
