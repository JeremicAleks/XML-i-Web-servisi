package com.reservation.microservice.controller;

import com.reservation.microservice.domain.reservation.*;
import com.reservation.microservice.service.MessageTableService;
import com.reservation.microservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationController {

    @Autowired
    ReservationService reservationService;
    @Autowired
    MessageTableService messageTableService;

    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetReservations getReservatons(){
    	List<Reservation> reservations = reservationService.findAll();
    	GetReservations getReservations = new GetReservations();

    	for(Reservation res : reservations)
    	    getReservations.getReservation().add(res);


        return getReservations;
    }

    @GetMapping(value = "/all/{username}",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetReservations getReservaton(@PathVariable String username){
        List<Reservation> reservations = reservationService.findAllByUsername(username);
        GetReservations getReservations = new GetReservations();

        for(Reservation res : reservations)
            getReservations.getReservation().add(res);


        return getReservations;
    }

    @PostMapping(value = "/add")
    public Reservation reserve(@RequestBody ReservationDTO reservationDTO){
        Reservation reservation;

       reservation = reservationService.reserveRoom(reservationDTO);


        return reservation;
    }

    @GetMapping(value ="/allMessage/{username}")
    public GetMessages getMessagesForUser(@PathVariable String username){
        GetMessages getMessages = new GetMessages();

        List<MessageTable> messageTables = messageTableService.getMessageForUser(username);
        for (MessageTable messageTable:messageTables)
            getMessages.getMessageTable().add(messageTable);


        return getMessages;
    }

}
