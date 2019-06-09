package com.centralapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reservation")
public class ReservationController {


    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReservatons(){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReservaton(@PathVariable Long id){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping(value = "/add/{idRoom}")
    public ResponseEntity<?> reserve(@PathVariable Long idRoom){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
