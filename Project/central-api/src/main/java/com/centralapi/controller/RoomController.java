package com.centralapi.controller;

import com.centralapi.domain.xml.xml_ftn.rooms.GetRooms;
import com.centralapi.domain.xml.xml_ftn.rooms.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("api/room")
public class RoomController {
	
	@Autowired
	RestTemplate rest;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRooms(){

        GetRooms getRooms = restTemplate.getForObject("https://room-microservice/api/all",GetRooms.class);

        return new ResponseEntity<>(getRooms.getRoom(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRoom(@PathVariable Long id){

        Room room = restTemplate.getForObject("https://room-microservice/api/"+id,Room.class);


        return new ResponseEntity<>(room, HttpStatus.OK);
    }
    
	@GetMapping(value="/test",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> testsearch() {
		System.out.println("fsafsafa");
		ResponseEntity<String> temp = rest.exchange("https://room-microservice/api/test", HttpMethod.GET, null, String.class);
		return new ResponseEntity<>(temp.getBody(), HttpStatus.OK);

    }
}
