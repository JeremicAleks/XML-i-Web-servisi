package com.centralapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.centralapi.domain.dto.ClientReservationDTO;
import com.centralapi.domain.dto.ClientSendMessageDTO;
import com.centralapi.domain.dto.ShowMessageForUserDTO;
import com.centralapi.domain.xml.xml_ftn.reservation.GetReservations;
import com.centralapi.domain.xml.xml_ftn.reservation.Reservation;

@RestController
@RequestMapping("api/reservation")
public class ReservationController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/allReservation",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReservatons(){

        GetReservations getReservations = restTemplate.getForObject("https://reservation-microservice/api/reservation/all",GetReservations.class);
        return new ResponseEntity<>(getReservations.getReservation(),HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getReservationForUser(){
        GetReservations getReservations;
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        getReservations = restTemplate.getForObject("https://reservation-microservice/api/reservation/all/"+username,GetReservations.class);

        return new ResponseEntity<>(getReservations.getReservation(), HttpStatus.OK);
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

       String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
       clientReservationDTO.setUsername(username);

        reservation = restTemplate.postForObject("https://reservation-microservice/api/reservation/addFromClient",clientReservationDTO,Reservation.class);

        if (reservation == null)
            return new ResponseEntity<>("Postoji rezervacija za tu sobu",HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }


    @PostMapping(value = "/sendMessageClient")
    public ResponseEntity<?> sendMessage(@RequestBody ClientSendMessageDTO clientSendMessageDTO){
        Reservation reservation;
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        clientSendMessageDTO.setUsername(username);
        reservation = restTemplate.postForObject("https://reservation-microservice/api/reservation/sendMessageClient",clientSendMessageDTO,Reservation.class);

        return new ResponseEntity<>(reservation,HttpStatus.OK);
    }

    @PostMapping(value ="/cancel/{idReservation}")
    public ResponseEntity<?> reservationCancel(@PathVariable Long idReservation){
        System.out.println("id rezervacije:" + idReservation);
        Reservation reservation;
//        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        System.out.println("id rezervacije:" + idReservation);
        reservation = restTemplate.postForObject("https://reservation-microservice/api/reservation/cancel/"+idReservation,null,Reservation.class);

        return new ResponseEntity<>(reservation,HttpStatus.OK);
    }

    @GetMapping(value = "/getMessageForUser/{idReservation}")
    public ResponseEntity<?> getMessageForUser(@PathVariable Long idReservation){
        ShowMessageForUserDTO  mess ;

        mess = restTemplate.getForObject("https://reservation-microservice/api/reservation/showMessage/"+idReservation,ShowMessageForUserDTO.class);

        return new ResponseEntity<>(mess,HttpStatus.OK);
    }

    
	@GetMapping(value="/test",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> testsearch() {
		System.out.println("fsafsafa");
		ResponseEntity<String> temp =restTemplate.exchange("https://reservation-microservice/api/reservation/test", HttpMethod.GET, null, String.class);
		return new ResponseEntity<>(temp.getBody(), HttpStatus.OK);

    }


}
