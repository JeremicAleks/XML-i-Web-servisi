package com.centralapi.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/reservation")
public class ReservationController {


    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReservatons(){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReservaton(){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = "/add")
    public ResponseEntity<?> reserve(){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
