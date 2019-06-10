package com.reservation.microservice.controller;

import com.reservation.microservice.domain.Reservation;
import com.reservation.microservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservation> getReservatons(){



        return null;
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Reservation getReservaton(@PathVariable Long id){
        Reservation reservation = reservationService.findById(id);

        return reservation;
    }

    @PostMapping(value = "/add/{idRoom}")
    public Reservation reserve(@PathVariable Long idRoom){

        return null;
    }

}
