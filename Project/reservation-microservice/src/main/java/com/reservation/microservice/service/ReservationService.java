package com.reservation.microservice.service;

import java.util.Date;
import java.util.List;

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

        Reservation reservation = new Reservation();
        reservation.setCheckIn(reservationDTO.getReservation().getCheckIn());
        reservation.setCheckOut(reservationDTO.getReservation().getCheckOut());
        reservation.setState(reservationDTO.getReservation().getState());
        List<MessageTable> messageTables = reservationDTO.getReservation().getMessageTable();

        for(MessageTable mt:messageTables) {
            MessageTable messageTable = new MessageTable();
            messageTable.setFromUser(mt.getFromUser());
            messageTable.setToUser(mt.getToUser());
            messageTable.setMessageString(mt.getMessageString());

            messageTable = messageTableService.save(messageTable);
            reservation.getMessageTable().add(messageTable);
        }

        reservation = save(reservation);

        if (reservationDTO.getUserId() != 0L){
            RegistredUser registredUser = registeredUserService.findById(reservationDTO.getUserId());
            registredUser.getReservation().add(reservation);
            registeredUserService.save(registredUser);
        }

        Room room = roomService.fingById(reservationDTO.getRoomId());
        room.getReservation().add(reservation);
        room = roomService.save(room);


        return reservation;
    }
}