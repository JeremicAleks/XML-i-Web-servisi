package com.centralapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.centralapi.domain.xml.xml_ftn.rooms.RateAndComment;

@Service
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	RestTemplate restTemp;

	@GetMapping(value = "/getComments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> search() {

		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("pogodjen central api - getComments");
		
		ResponseEntity<List<RateAndComment>> response = restTemp.exchange("http://rate-comment-microservice/api/rates/all",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<RateAndComment>>() {});
		
        List<RateAndComment> searchResults =  response.getBody();
		System.out.println("nesto se desava");

		return new ResponseEntity<>(searchResults, HttpStatus.OK);
		// return new ResponseEntity<>(searchResults, reply.getStatusCode());
	}
}
