package com.reservation.microservice.controller;

import com.reservation.microservice.domain.dto.ClientReservationDTO;
import com.reservation.microservice.domain.dto.ClientSendMessageDTO;
import com.reservation.microservice.domain.dto.GetRoomIdDTO;
import com.reservation.microservice.domain.dto.ShowMessageForUserDTO;
import com.reservation.microservice.domain.reservation.*;
import com.reservation.microservice.domain.user.SendMessageDTO;
import com.reservation.microservice.service.MessageTableService;
import com.reservation.microservice.service.ReservationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;
    @Autowired
    MessageTableService messageTableService;

    public static Logger logger = Logger.getLogger(ReservationController.class);
    
	@PreAuthorize("hasRole('ROLE_CENTRAL_APP')")
	@GetMapping(value = "/test",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> test(){
		// @RequestBody SearchParamsDTO spDTO

		System.out.println("kklslsjl");
        return new ResponseEntity<>("dsafsafsaf", HttpStatus.OK);
    }

    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetReservations getReservatons(){
    	List<Reservation> reservations = reservationService.findAll();
    	GetReservations getReservations = new GetReservations();

    	for(Reservation res : reservations)
    	    getReservations.getReservation().add(res);

        logger.info("All reservations successfully taken!");
        return getReservations;
    }

    @GetMapping(value = "/all/{username}",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetReservations getReservaton(@PathVariable String username){
        List<Reservation> reservations = reservationService.findAllByUsername(username);
        GetReservations getReservations = new GetReservations();

        for(Reservation res : reservations)
            getReservations.getReservation().add(res);

        logger.info("All reservations for "  + username + "successfully taken!");
        return getReservations;
    }

    @PostMapping(value = "/add")
    public Reservation reserve(@RequestBody ReservationDTO reservationDTO){
        Reservation reservation;

       reservation = reservationService.reserveRoom(reservationDTO);

    logger.info("Reservation successfully created!");
        return reservation;
    }

    @PostMapping(value ="/addFromClient")
    public Reservation reserveClient(@RequestBody ClientReservationDTO clientReservationDTO){
	    Reservation reservation;

	    reservation = reservationService.reserveRoomClient(clientReservationDTO);

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

    @PostMapping(value = "/changeState")
    public Reservation changeState(@RequestBody AllowReservationDTO allowReservationDTO){
        Reservation reservation;

        reservation = reservationService.changeState(allowReservationDTO);
        logger.info("State successfully changed!");
        return reservation;
    }

    @PostMapping(value = "/sendMessage")
    public Reservation sendMessage(@RequestBody SendMessageDTO sendMessageDTO){
        Reservation reservation;

        reservation = messageTableService.sendMessage(sendMessageDTO);
        logger.info("Message successfully sent!");
        return reservation;
    }

    @PostMapping(value = "/sendMessageClient")
    public Reservation sendMessageClient(@RequestBody ClientSendMessageDTO clientSendMessageDTO){
	    Reservation reservation;

	    reservation = messageTableService.sendMessageClient(clientSendMessageDTO);

	    return reservation;
    }

    @PostMapping(value = "/cancel/{idReservation}")
    public Reservation cancelReservation(@PathVariable Long idReservation){
	    Reservation reservation;

	    reservation = reservationService.cancelReservation(idReservation);

        return reservation;
    }

    @GetMapping(value = "/showMessage/{idReservation}")
    public ShowMessageForUserDTO getMessageForUser(@PathVariable Long idReservation){
        ShowMessageForUserDTO mess;

	    mess = reservationService.getMessagesForUser(idReservation);

	    return mess;
    }

    @GetMapping(value = "/getIdForRoom/{idReservation}")
    public GetRoomIdDTO getRoomId(@PathVariable Long idReservation){
	    GetRoomIdDTO getRoomIdDTO = new GetRoomIdDTO();
	    Long id = reservationService.getRoomId(idReservation);

	    getRoomIdDTO.setId(id);

	    return getRoomIdDTO;
    }



}
