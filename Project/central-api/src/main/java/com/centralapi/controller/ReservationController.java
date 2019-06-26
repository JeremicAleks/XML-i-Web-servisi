package com.centralapi.controller;

import java.util.List;

import com.centralapi.domain.dto.ClientReservationDTO;
import com.centralapi.domain.dto.ClientSendMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.centralapi.domain.xml.xml_ftn.reservation.Reservation;

@RestController
@RequestMapping("api/reservation")
public class ReservationController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReservatons(){

        ResponseEntity<List<Reservation>> response = restTemplate.exchange("https://reservation-microservice/api/reservation/all",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Reservation>>() {});
        return new ResponseEntity<>(response.getBody(),HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReservaton(@PathVariable Long id){
        
    	Reservation reservation = restTemplate.getForObject("https://reservation-microservice/api/reservation/"+id,Reservation.class);

        return new ResponseEntity<>(reservation,HttpStatus.OK);
    }

    //rezervacija sobe
    @PostMapping(value = "/add")
    public ResponseEntity<?> reserve(@RequestBody ClientReservationDTO clientReservationDTO){
        Reservation reservation;

        reservation = restTemplate.postForObject("https://reservation-microservice/api/reservation/addFromClient",clientReservationDTO,Reservation.class);

        if (reservation == null)
            return new ResponseEntity<>("Postoji rezervacija za tu sobu",HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PostMapping(value = "/sendMessageClient")
    public ResponseEntity<?> sendMessage(@RequestBody ClientSendMessageDTO clientSendMessageDTO){
        Reservation reservation;

        reservation = restTemplate.postForObject("https://reservation-microservice/api/reservation/sendMessage",clientSendMessageDTO,Reservation.class);

        return new ResponseEntity<>(reservation,HttpStatus.OK);
    }
    
	@GetMapping(value="/test",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> testsearch() {
		System.out.println("fsafsafa");
		ResponseEntity<String> temp =restTemplate.exchange("https://reservation-microservice/api/reservation/test", HttpMethod.GET, null, String.class);
		return new ResponseEntity<>(temp.getBody(), HttpStatus.OK);

    }


}
