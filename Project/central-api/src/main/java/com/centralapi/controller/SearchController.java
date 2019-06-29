package com.centralapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.centralapi.domain.SearchParamsDTO;
import com.centralapi.domain.xml.xml_ftn.rooms.GetRooms;
import com.centralapi.domain.xml.xml_ftn.rooms.Room;

@Service
@RequestMapping("/api/search")
public class SearchController {
	
	@Autowired
	RestTemplate restTemp;
	@Autowired
	RestTemplate rest;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> search(@RequestBody SearchParamsDTO sdto) {
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("pogodjen central api");

		ResponseEntity<GetRooms> temp = rest.exchange("https://search-microservice/api/search", HttpMethod.POST, new HttpEntity<>(sdto), GetRooms.class);
		

		System.out.println("nesto se desava");
		
		return new ResponseEntity<>(temp.getBody().getRoom(), HttpStatus.OK);
		//return new ResponseEntity<>(searchResults, reply.getStatusCode());
	}
	
	@GetMapping(value="/test",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> testsearch() {
		System.out.println("fsafsafa");
		ResponseEntity<String> temp = rest.exchange("https://search-microservice/api/search/test", HttpMethod.GET, null, String.class);
		return new ResponseEntity<>(temp.getBody(), HttpStatus.OK);

    }

}
