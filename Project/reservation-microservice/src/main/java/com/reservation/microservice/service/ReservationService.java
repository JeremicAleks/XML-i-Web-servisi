package com.reservation.microservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.reservation.microservice.domain.dto.ClientReservationDTO;
import com.reservation.microservice.domain.reservation.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public static Logger logger = Logger.getLogger(ReservationService.class);

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
        logger.info("");
        return reservations;
    }

    public Reservation reserveRoom(ReservationDTO reservationDTO) {
        Room room = roomService.findById(reservationDTO.getRoomId());

        List<Reservation> reservations = reservationsRoomForDate(room,reservationDTO.getReservation().getCheckIn(),reservationDTO.getReservation().getCheckOut());
        if(reservations.isEmpty()){
            Reservation res = reservationRepository.save(reservationDTO.getReservation());
            if (reservationDTO.getUserId() != null){
                RegistredUser registredUser = registeredUserService.findByUsername(reservationDTO.getUserId());
                registredUser.getReservation().add(res);
                registeredUserService.save(registredUser);
            }
            room.getReservation().add(res);
            roomService.save(room);
            return res;
        }else{
            return  null;
        }

    }

    public Reservation changeState(AllowReservationDTO allowReservationDTO) {

        Reservation reservation = findById(allowReservationDTO.getReservationId());
        reservation.setState(allowReservationDTO.getState());

        reservation = save(reservation);

        return reservation;
    }

    public List<Reservation> reservationsRoomForDate(Room room,Date startDate,Date endDate){
        List<Reservation> res = new ArrayList<>();
        List<Reservation> all = room.getReservation();

        for (Reservation r : all){
            if((startDate.before(r.getCheckIn()) && endDate.after(r.getCheckIn()))|| (startDate.before(r.getCheckOut())&&endDate.after(r.getCheckOut()))
            || (startDate.after(r.getCheckIn())&& endDate.before(r.getCheckOut()))|| (startDate.before(r.getCheckIn())&&endDate.after(r.getCheckOut())) || startDate.equals(r.getCheckIn()) || endDate.equals(r.getCheckOut())){
                res.add(r);
            }
        }
        return res;
    }

    public Reservation reserveRoomClient(ClientReservationDTO clientReservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setState(ReservationStateEnum.PENDING);
        reservation.setCheckIn(clientReservationDTO.getCheckIn());
        reservation.setCheckOut(clientReservationDTO.getCheckOut());

        Room room = roomService.findById(clientReservationDTO.getRoomId());

        List<Reservation> reservations = reservationsRoomForDate(room,reservation.getCheckIn(),reservation.getCheckOut());

        if (reservations.isEmpty()){
            reservation = save(reservation);
            RegistredUser registredUser = registeredUserService.findByUsername(clientReservationDTO.getUsername());
            registredUser.getReservation().add(reservation);
            registeredUserService.save(registredUser);
            return reservation;
        }else
            return null;


    }
}