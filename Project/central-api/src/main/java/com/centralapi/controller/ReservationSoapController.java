package com.centralapi.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.centralapi.domain.xml.xml_ftn.reservation.GetReservations;
import com.centralapi.domain.xml.xml_ftn.rooms.AddRoomDTO;
import com.centralapi.domain.xml.xml_ftn.rooms.Room;
import com.centralapi.domain.xml.xml_ftn.users.GetReservationForUserDTO;

@Endpoint
public class ReservationSoapController {
	
	private static final String ROOM_NAMESPACE_URI = "http://www.xml-ftn.xml.domain.centralapi.com/Rooms";
	private static final String RESERVATION_NAMESPACE_URI = "http://www.xml-ftn.xml.domain.centralapi.com/Reservations";
	
	@PreAuthorize("hasRole('ROLE_AGENT_APP')")
    @PayloadRoot(namespace = RESERVATION_NAMESPACE_URI, localPart = "GetReservationForUserDTO")
    @ResponsePayload
    public GetReservations getReservations(@RequestPayload GetReservationForUserDTO  request) {
    	
		//vratiti sve reservacije :D
		
		System.out.println("Res");
    	
    	return new GetReservations();
        
    }

}
