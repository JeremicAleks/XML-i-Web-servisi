package com.reservation.microservice.service;

import com.reservation.microservice.domain.Reservation;
import com.reservation.microservice.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation findById(Long id){return reservationRepository.findById(id).get();}

    public List<Reservation> findAll(){return  reservationRepository.findAll();}

    public Reservation save(Reservation flightReservation){ return  reservationRepository.save(flightReservation);}

    public List<Reservation> findBetween(Date checkIn, Date checkOut){return reservationRepository.findByCheckInBetween(checkIn,checkOut);}

}