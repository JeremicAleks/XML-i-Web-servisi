package com.centralapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.centralapi.domain.xml.xml_ftn.reservation.Reservation;

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
    
	@GetMapping(value="/test",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> testsearch() {
		System.out.println("fsafsafa");
		ResponseEntity<String> temp =restTemplate.exchange("https://reservation-microservice/api/reservation/test", HttpMethod.GET, null, String.class);
		return new ResponseEntity<>(temp.getBody(), HttpStatus.OK);

    }


}
