package com.centralapi.controller;

import java.util.List;

import com.centralapi.exception.ResponseMessage;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.centralapi.domain.xml.xml_ftn.rooms.RateAndComment;

@Service
@RequestMapping("/api/comment")
public class CommentController {

	@GetMapping(value = "/getComments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getComments() {

		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("pogodjen central api - getComments");

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<RateAndComment>> response = restTemplate.exchange("http://ec2-35-180-34-177.eu-west-3.compute.amazonaws.com:8080/api/rates/all",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<RateAndComment>>() {
				});

		List<RateAndComment> searchResults = response.getBody();
		System.out.println("nesto se desava");

		return new ResponseEntity<>(searchResults, HttpStatus.OK);
	}

	@GetMapping(value = "/approveComment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> approveComment(@PathVariable Long id) {

		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("pogodjen central api - approveComment");

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.exchange("http://ec2-35-180-34-177.eu-west-3.compute.amazonaws.com:8080/api/rates/allow/" + id.toString(),
				HttpMethod.GET, null, String.class);
		System.out.println("nesto se desava");
		System.out.println(response.getBody());

		return new ResponseEntity<>(new ResponseMessage(response.getBody()), HttpStatus.OK);
	}
}
