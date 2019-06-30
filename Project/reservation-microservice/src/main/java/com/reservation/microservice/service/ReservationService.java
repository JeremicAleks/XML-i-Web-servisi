package com.reservation.microservice.service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.reservation.microservice.domain.dto.ClientReservationDTO;
import com.reservation.microservice.domain.dto.ShowMessageForUserDTO;
import com.reservation.microservice.domain.reservation.*;
import com.reservation.microservice.domain.room.PriceList;
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

        List<Reservation> res =  reservations.stream().filter(reservation -> reservation.getState()!=ReservationStateEnum.NOTALLOWED).collect(Collectors.toList());

        logger.info("");
        return res;
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
            if (r.getState()!=ReservationStateEnum.NOTALLOWED) {
                if ((startDate.before(r.getCheckIn()) && endDate.after(r.getCheckIn())) || (startDate.before(r.getCheckOut()) && endDate.after(r.getCheckOut()))
                        || (startDate.after(r.getCheckIn()) && endDate.before(r.getCheckOut())) || (startDate.before(r.getCheckIn()) && endDate.after(r.getCheckOut())) || startDate.equals(r.getCheckIn()) || endDate.equals(r.getCheckOut())) {
                    res.add(r);
                }
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

        boolean flag = false;

        for(PriceList pl:room.getPriceList()){
            if(havePriceListForDate(pl.getMonth(),reservation.getCheckIn()))
                flag = true;
        }

        List<Reservation> reservations = reservationsRoomForDate(room,reservation.getCheckIn(),reservation.getCheckOut());

        if (reservations.isEmpty() && flag){
            reservation = save(reservation);
            RegistredUser registredUser = registeredUserService.findByUsername(clientReservationDTO.getUsername());
            registredUser.getReservation().add(reservation);
            registeredUserService.save(registredUser);
            room.getReservation().add(reservation);
            roomService.save(room);
            return reservation;
        }else
            return null;


    }

    public Reservation cancelReservation(Long idReservation) {

        Reservation reservation = findById(idReservation);
        Long room_id = roomService.room_id(idReservation);
        Room room = roomService.findById(room_id);

        if (dateCheck(reservation.getCheckIn(),room.getDaysForCancel())){
            reservation.setState(ReservationStateEnum.NOTALLOWED);
            reservation= save(reservation);
        }

        return reservation;
    }

    public boolean dateCheck(Date checkIn,int canceldays){
        Date today = Calendar.getInstance().getTime();
        int days=0;
        if (today.before(checkIn)){
            days= (int) ((checkIn.getTime() - today.getTime())/ (1000*60*60*24));
        }

        return days>canceldays;
    }

    public boolean havePriceListForDate(Date date1, Date date2) {
        boolean ret = true;
        SimpleDateFormat x = new SimpleDateFormat("yyyy.MM.dd.");

        String date1String[] = x.format(date1).split("\\.");
        String date2String[] = x.format(date2).split("\\.");

        int date1year = Integer.parseInt(date1String[0]);
        int date2year = Integer.parseInt(date2String[0]);

        if (date1year != date2year)
            return false;

        int date1month = Integer.parseInt(date1String[1]);
        int date2month = Integer.parseInt(date2String[1]);

        if (date1month != date2month)
            return false;


        return ret;
    }

    public ShowMessageForUserDTO getMessagesForUser(Long idReservation) {
        ShowMessageForUserDTO showMessageForUserDTO = new ShowMessageForUserDTO();
        Reservation reservation = findById(idReservation);
        Long roomId = roomService.room_id(idReservation);
        Room room = roomService.findById(roomId);
        showMessageForUserDTO.setReservation(reservation);
        showMessageForUserDTO.setRoom(room);
        showMessageForUserDTO.setMessageTable(reservation.getMessageTable());

        return showMessageForUserDTO;
    }

    public Long getRoomId(Long idReservation) {
        Long id = roomService.room_id(idReservation);
        return id;
    }
}
