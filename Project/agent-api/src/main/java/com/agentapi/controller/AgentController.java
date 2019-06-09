package com.agentapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.agentapi.config.SOAPConnector;
import com.centralapi.domain.xml.xml_ftn.users.UserLoginDTO;

@RestController
@RequestMapping("/api")
public class AgentController {
	
	static final String SERVICE_URI = "https://localhost:8043/ws/users";
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
    WebServiceTemplate soap;
	
    @PostMapping(value = "/addRoom",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addRoom(){

        return new ResponseEntity<>(null, HttpStatus.OK);
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
