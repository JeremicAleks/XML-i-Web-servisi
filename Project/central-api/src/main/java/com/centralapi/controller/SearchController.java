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

	@GetMapping(value = "/kurec", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> search() {
		
		System.out.println("pogodjen central api");
		GetRooms rooms = restTemp.getForObject("http://search-microservice/api/search/kurec", GetRooms.class);
		List<Room> searchResults = rooms.getRoom();

		return new ResponseEntity<>(searchResults, HttpStatus.OK);
	}

}
