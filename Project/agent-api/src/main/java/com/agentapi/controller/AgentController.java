package com.agentapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AgentController {


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


}
