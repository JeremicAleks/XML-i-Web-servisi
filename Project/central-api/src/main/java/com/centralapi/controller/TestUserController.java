package com.centralapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centralapi.repo.UserRepository;

@RestController
@RequestMapping("/api/module")
public class TestUserController {
	
	@Autowired
    UserRepository userRepo;
	
	@PreAuthorize("hasRole('ROLE_AGENT_APP')")
	@GetMapping(value="/test",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findCertificates() {

        return new ResponseEntity<>("Usera ima: " + userRepo.count(), HttpStatus.OK);
    }

}
