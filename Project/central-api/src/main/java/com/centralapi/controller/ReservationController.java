package com.centralapi.controller;

import com.centralapi.domain.xml.xml_ftn.reservation.Reservation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/reservation")
public class ReservationController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservation> getReservatons(){

        ResponseEntity<List<Reservation>> response = restTemplate.exchange("http://reservation-microservice/api/reservation/all",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Reservation>>() {});
        return response.getBody();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Reservation getReservaton(@PathVariable Long id){
        
    	Reservation reservation = restTemplate.getForObject("http://reservation-microservice/api/reservation/"+id,Reservation.class);

        return reservation;
    }

    @PostMapping(value = "/add/{idRoom}")
    public ResponseEntity<?> reserve(@PathVariable Long idRoom){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
