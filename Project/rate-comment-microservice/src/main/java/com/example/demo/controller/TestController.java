package com.example.demo.controller;

import javax.xml.ws.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/api")
public class TestController {
	
	@GetMapping(name = "/test")
	public ResponseEntity<?> testMethode() {
		
		return new ResponseEntity<String>("Test", HttpStatus.OK);
	}

}
