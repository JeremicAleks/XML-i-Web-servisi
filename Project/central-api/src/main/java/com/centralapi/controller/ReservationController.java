package com.centralapi.controller;

import com.centralapi.domain.xml.xml_ftn.reservation.Reservation;
import com.centralapi.domain.xml.xml_ftn.reservation.ReservationDTO;
import com.centralapi.domain.xml.xml_ftn.rooms.Room;
import com.centralapi.service.ReservationService;
import com.centralapi.service.RoomService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

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
