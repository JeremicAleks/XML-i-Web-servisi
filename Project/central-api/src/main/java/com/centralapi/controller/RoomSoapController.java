package com.centralapi.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.centralapi.domain.xml.xml_ftn.reservation.ReservationDTO;
import com.centralapi.domain.xml.xml_ftn.rooms.Image;
import com.centralapi.domain.xml.xml_ftn.rooms.Room;
import com.centralapi.exception.ResponseMessage;


@Endpoint
public class RoomSoapController {
	
	private static final String ROOM_NAMESPACE_URI = "http://www.xml-ftn.xml.domain.centralapi.com/Rooms";
	private static final String RESERVATION_NAMESPACE_URI = "http://www.xml-ftn.xml.domain.centralapi.com/Reservations";
	
	@PreAuthorize("hasRole('ROLE_AGENT_APP')")
    @PayloadRoot(namespace = ROOM_NAMESPACE_URI, localPart = "Room")
    @ResponsePayload
    public Room addRoom(@RequestPayload Room  request) {
    	
		//sacuvati u bazu i vratiti room sa id-om
    	System.out.println(request.getAdditionalServices());
    	
    	return request;
        
    }
	
	@PreAuthorize("hasRole('ROLE_AGENT_APP')")
    @PayloadRoot(namespace = ROOM_NAMESPACE_URI, localPart = "image")
    @ResponsePayload
    public Image addFile(@RequestPayload Image  request) throws IOException {
		
		//ovo  mozes staviti negde u neki service ili kako god :D
		Path path = Paths.get("src", "main", "resources", "static");
	    ByteArrayInputStream bis = new ByteArrayInputStream(request.getImage());
		BufferedImage bImage2 = ImageIO.read(bis);
	    ImageIO.write(bImage2, "jpg", new File(path.toString()+"/"+request.getRoomId()+"-"+request.getNameForImage()));
    	
    	return request;
        
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
