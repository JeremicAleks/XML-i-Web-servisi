package com.reservation.microservice.service;

import java.util.Date;
import java.util.List;

import com.reservation.microservice.domain.reservation.AllowReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservation.microservice.domain.reservation.MessageTable;
import com.reservation.microservice.domain.reservation.Reservation;
import com.reservation.microservice.domain.reservation.ReservationDTO;
import com.reservation.microservice.domain.room.Room;
import com.reservation.microservice.domain.user.RegistredUser;
import com.reservation.microservice.repository.ReservationRepository;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RegisteredUserService registeredUserService;

    @Autowired
    private MessageTableService messageTableService;

    @Autowired
    private RoomService roomService;

    public Reservation findById(Long id){return reservationRepository.findById(id).get();}

    public List<Reservation> findAll(){return  reservationRepository.findAll();}

    public Reservation save(Reservation flightReservation){ return  reservationRepository.save(flightReservation);}

    //does nothing :D
    public List<Reservation> findBetween(Date checkIn, Date checkOut){return reservationRepository.findByCheckInBetween(checkIn,checkOut);}

    //returns all reservations from registered user
    public List<Reservation> findAllByUsername(String username) {
        List<Reservation> reservations;
        RegistredUser registeredUser = registeredUserService.findByUsername(username);
        reservations = registeredUser.getReservation();

        return reservations;
    }

    public Reservation reserveRoom(ReservationDTO reservationDTO) {

    	Reservation res = reservationRepository.save(reservationDTO.getReservation());
    	
        if (reservationDTO.getUserId() != null){
            RegistredUser registredUser = registeredUserService.findByUsername(reservationDTO.getUserId());
            registredUser.getReservation().add(res);
            registeredUserService.save(registredUser);
            System.out.println("ovde ne ulazi");
        }

        System.out.println("ovde ulazi");
        Room room = roomService.fingById(reservationDTO.getRoomId());
        room.getReservation().add(res);
        roomService.save(room);


        return res;
    }

    public Reservation changeState(AllowReservationDTO allowReservationDTO) {

        Reservation reservation = findById(allowReservationDTO.getReservationId());
        reservation.setState(allowReservationDTO.getState());

        reservation = save(reservation);

        return reservation;
    }
}