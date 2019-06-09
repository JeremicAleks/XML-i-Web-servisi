package com.centralapi.controller;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.centralapi.domain.xml.xml_ftn.reservation.ReservationDTO;
import com.centralapi.domain.xml.xml_ftn.rooms.AddRoomDTO;
import com.centralapi.exception.ResponseMessage;


@Endpoint
public class RoomSoapController {
	
	private static final String ROOM_NAMESPACE_URI = "http://www.xml-ftn.xml.domain.centralapi.com/Rooms";
	private static final String RESERVATION_NAMESPACE_URI = "http://www.xml-ftn.xml.domain.centralapi.com/Reservations";
	

    @PayloadRoot(namespace = ROOM_NAMESPACE_URI, localPart = "addRoomDTO")
    @ResponsePayload
    public ResponseMessage addRoom(@RequestPayload AddRoomDTO  request) {
    	
    	return null;
        
    }
  /*  
    @PayloadRoot(namespace = ROOM_NAMESPACE_URI, localPart = "ReservationDTO")
    @ResponsePayload
    public ResponseMessage addUnavailableRoom(@RequestPayload ReservationDTO  request) {
    	
    	return null;
        
    }*/
    
    @PayloadRoot(namespace = ROOM_NAMESPACE_URI, localPart = "ReservationDTO")
    @ResponsePayload
    public ResponseMessage roomReservationManagement(@RequestPayload ReservationDTO  request) {
    	
    	return null;
        
    }
}
