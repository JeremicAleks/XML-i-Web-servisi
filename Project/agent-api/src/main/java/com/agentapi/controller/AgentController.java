package com.agentapi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.Image;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.Room;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.users.UserLoginDTO;
import com.agentapi.repo.RoomRepository;

@RestController
@RequestMapping("/api")
public class AgentController {
	
	static final String SERVICE_URI = "https://localhost:8043/ws/";
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	RoomRepository roomRepo;
	
	@Autowired
    WebServiceTemplate soap;
	
    @PostMapping(value = "/addRoom")
    public ResponseEntity<?> addRoom(@RequestBody Room room){
    	
    	System.out.println(room.getDescription()+room.getAdditionalServices()+room.getDescription()+room.getNumberOfBeds());
    
    	
    	roomRepo.save(room);
    	
    	Room response = (Room) soap.
    			marshalSendAndReceive(SERVICE_URI, room);
    	
        return new ResponseEntity<>(response.getAdditionalServices(), HttpStatus.OK);
    }
    
    @PostMapping(value = "/addFile")
    public ResponseEntity<?> addFile(@RequestParam(value="file") MultipartFile files,@RequestParam("roomString") Long roomS) throws IOException{
    	
    	System.out.println(files.getOriginalFilename());
    	Image im = new Image();
    	im.setImage(files.getBytes());
    	im.setNameForImage(files.getOriginalFilename());
    	im.setRoomId(roomS);
    	
     	Image i = (Image) soap.
    			marshalSendAndReceive(SERVICE_URI, im);
     	
		return new ResponseEntity<>("uspesnooooo",HttpStatus.OK);
		
    }

    @PostMapping(value = "/reserveRoom/{idRoom}")
    public ResponseEntity<?> reserveRoom(){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = "message/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMessage(){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping(value = "/message/{idUser}")
    public ResponseEntity<?> sendMessage(){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping(value = "/reservation/{id}")
    public ResponseEntity<?> confirmReservation(){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody UserLoginDTO login){
    	
    	System.out.println("Login:  " + login.getUsername());
    	
    	UserLoginDTO response = (UserLoginDTO) soap.
    			marshalSendAndReceive(SERVICE_URI, login);

    	//ResponseEntity<String> resp = restTemplate.postForEntity(SERVICE_URI, entity, String.class);
        System.out.println("RADI LIII: " + response.getUsername() + " " + response.getPassword());
    	return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
