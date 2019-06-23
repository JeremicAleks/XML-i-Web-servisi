package com.centralapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.centralapi.domain.xml.xml_ftn.rooms.GetRooms;
import com.centralapi.domain.xml.xml_ftn.rooms.Room;

@Service
@RequestMapping("/api/search")
public class SearchController {
	
	@Autowired
	RestTemplate restTemp;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> search() {
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("pogodjen central api");
		
		//ResponseEntity<?> reply = restTemp.getForObject("http://search-microservice/api/search", ResponseEntity.class);
		//System.out.println("progutao je ovaj moj pokusaj - reply");
		//GetRooms getRooms = (GetRooms) reply.getBody();
		GetRooms getRooms = restTemp.getForObject("http://search-microservice/api/search", GetRooms.class);
		List<Room> searchResults = getRooms.getRoom();
		System.out.println("nesto se desava");
		
		return new ResponseEntity<>(searchResults, HttpStatus.OK);
		//return new ResponseEntity<>(searchResults, reply.getStatusCode());
	}

}
