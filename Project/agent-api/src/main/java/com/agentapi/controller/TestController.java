package com.agentapi.controller;

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

	static final String SERVICE_URI = "https://localhost:8043/api/module/test";

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findCertificates() {
		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<String> resp = restTemplate.exchange(SERVICE_URI, HttpMethod.GET, null, String.class);
			return new ResponseEntity<>(resp.getBody()+" ||Test centralnog servera!", HttpStatus.OK);
		} catch (Exception e) {
		}
		return new ResponseEntity<>("Acces Denied", HttpStatus.FORBIDDEN);

	}

}
