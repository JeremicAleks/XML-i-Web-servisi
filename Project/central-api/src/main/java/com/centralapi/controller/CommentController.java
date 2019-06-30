package com.centralapi.controller;

import java.util.List;

import com.centralapi.domain.dto.AddRateAndCommentDTO;
import com.centralapi.domain.dto.GetRoomIdDTO;
import com.centralapi.exception.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.centralapi.domain.xml.xml_ftn.rooms.RateAndComment;

@Service
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	private RestTemplate rest;

	@GetMapping(value = "/getComments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getComments() {

		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("pogodjen central api - getComments");

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<RateAndComment>> response = restTemplate.exchange("http://ec2-35-181-44-209.eu-west-3.compute.amazonaws.com:8048/api/rates/all",
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

		ResponseEntity<String> response = restTemplate.exchange("http://ec2-35-181-44-209.eu-west-3.compute.amazonaws.com:8048/api/rates/allow/" + id.toString(),
				HttpMethod.GET, null, String.class);
		System.out.println("nesto se desava");
		System.out.println(response.getBody());

		return new ResponseEntity<>(new ResponseMessage(response.getBody()), HttpStatus.OK);
	}

	@PostMapping(value = "/addComment",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addComment (@RequestBody AddRateAndCommentDTO addRateAndCommentDTO){
		RestTemplate restTemplate = new RestTemplate();
		String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		GetRoomIdDTO getRoomIdDTO = rest.getForObject("https://reservation-microservice/api/reservation/getIdForRoom/"+addRateAndCommentDTO.getReservationId(),GetRoomIdDTO.class);
		addRateAndCommentDTO.setRoomId(getRoomIdDTO.getId());
		addRateAndCommentDTO.setUsername(username);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<AddRateAndCommentDTO> request = new HttpEntity<>(addRateAndCommentDTO, headers);
		ResponseEntity<RateAndComment> response = restTemplate.exchange("http://ec2-35-181-44-209.eu-west-3.compute.amazonaws.com:8048/api/rates/add",
				HttpMethod.POST, request, RateAndComment.class);

		return response;
	}
}
