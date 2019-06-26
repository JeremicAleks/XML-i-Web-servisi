package com.centralapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.centralapi.domain.xml.xml_ftn.reservation.AllowReservationDTO;
import com.centralapi.domain.xml.xml_ftn.reservation.GetMessages;
import com.centralapi.domain.xml.xml_ftn.reservation.GetReservations;
import com.centralapi.domain.xml.xml_ftn.reservation.MessageTable;
import com.centralapi.domain.xml.xml_ftn.reservation.Reservation;
import com.centralapi.domain.xml.xml_ftn.reservation.ReservationDTO;
import com.centralapi.domain.xml.xml_ftn.users.GetMessagesForUserDTO;
import com.centralapi.domain.xml.xml_ftn.users.GetReservationForUserDTO;
import com.centralapi.domain.xml.xml_ftn.users.SendMessageDTO;

@Endpoint
public class ReservationSoapController {
	
	private static final String ROOM_NAMESPACE_URI = "http://www.xml-ftn.xml.domain.centralapi.com/Rooms";
	private static final String RESERVATION_NAMESPACE_URI = "http://www.xml-ftn.xml.domain.centralapi.com/Reservation";
	private static final String USER_NAMESPACE_URI = "http://www.xml-ftn.xml.domain.centralapi.com/Users";

	@Autowired
    private RestTemplate restTemplate;

	@PreAuthorize("hasRole('ROLE_AGENT_APP')")
    @PayloadRoot(namespace = USER_NAMESPACE_URI, localPart = "GetReservationForUserDTO")
    @ResponsePayload
    public GetReservations getReservations(@RequestPayload GetReservationForUserDTO  request) {
		//vratiti sve reservacije :D

        GetReservations getReservations;

        getReservations = restTemplate.getForObject("http://reservation-microservice/api/all",GetReservations.class);
        //getReservations = restTemplate.getForObject("http://reservation-microservice/api/all/"+request.getUsername(),GetReservations.class);


    	return getReservations;
        
    }
	
    @PayloadRoot(namespace = USER_NAMESPACE_URI, localPart = "GetMessagesForUserDTO")
    @ResponsePayload
    public GetMessages getMsgs(@RequestPayload GetMessagesForUserDTO  request) {
	    GetMessages getMessages;

	    getMessages = restTemplate.getForObject("http://reservation-microservice/api/allMessage/"+request.getUsername(),GetMessages.class);


    	return getMessages;
    }

    @PayloadRoot(namespace = RESERVATION_NAMESPACE_URI,localPart = "ReservationDTO")
    @ResponsePayload
    public Reservation addReservation(@RequestPayload ReservationDTO request){
	    Reservation reservation;

        System.out.println("Dodavanje rezervacije");

	    reservation = restTemplate.postForObject("http://reservation-microservice/api/add",request,Reservation.class);

	    return reservation;
    }

    @PayloadRoot(namespace = RESERVATION_NAMESPACE_URI,localPart = "AllowReservationDTO")
    @ResponsePayload
    public Reservation changeStateReservation(@RequestPayload AllowReservationDTO request){
	    Reservation reservation;

	    reservation = restTemplate.postForObject("http://reservation-microservice/api/changeState",request,Reservation.class);

	    return reservation;
    }
    //mozda treba promeniti namespace :D
    @PayloadRoot(namespace = USER_NAMESPACE_URI,localPart = "SendMessageDTO")
    @ResponsePayload
    public MessageTable sendMessage(@RequestPayload SendMessageDTO request){
        MessageTable messageTable;

        messageTable = restTemplate.postForObject("http://reservation-microservice/api/sendMessage",request,MessageTable.class);

        return messageTable;
    }


}
