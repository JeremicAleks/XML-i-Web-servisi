package com.agentapi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

import org.apache.tomcat.util.net.SSLContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/api")
public class TestController {
	
	static final String SERVICE_URI = "https://localhost:8043/api/protect";
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findCertificates() {
	RestTemplate restTemplate = new RestTemplate();
	System.out.println("safasfa");
	try {
	ResponseEntity<String> resp =	restTemplate.exchange(SERVICE_URI, HttpMethod.GET, null,String.class);
	 return new ResponseEntity<>(resp.getBody()+" 321421", HttpStatus.OK);
	}
	catch (Exception e) {
		e.printStackTrace();
		
	}
	System.out.println("dsadada");
	 return new ResponseEntity<>("Ne radi", HttpStatus.OK);
     
    }
	

}
