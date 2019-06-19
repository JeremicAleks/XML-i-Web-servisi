package com.agentapi.controller;

import java.io.IOException;
import java.util.Collection;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.agentapi.com.centralapi.domain.xml.xml_ftn.reservation.GetMessages;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.reservation.GetReservations;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.reservation.Reservation;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.reservation.ReservationDTO;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.AddRoomDTO;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.GetRooms;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.Image;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.Room;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.users.GetMessagesForUserDTO;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.users.GetReservationForUserDTO;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.users.GetRoomsForUserDTO;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.users.LoginDTO;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.users.UserLoginDTO;
import com.agentapi.repo.MessageRepository;
import com.agentapi.repo.ReservationRepository;
import com.agentapi.repo.RoomRepository;

@RestController
@RequestMapping("/api")
public class AgentController {
	
	static final String SERVICE_URI = "https://localhost:8043/ws/";
	static final String USER_SERVICE_URI = "https://localhost:8043/ws/";
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ReservationRepository resRepo;
	
	@Autowired
	MessageRepository msgRepo;
	
	@Autowired
	RoomRepository roomRepo;
	
	@Autowired
    WebServiceTemplate soap;
	
    @PostMapping(value = "/addRoom")
    public ResponseEntity<?> addRoom(@RequestBody Room room){
    	
    	if(SecurityContextHolder.getContext().getAuthentication() == null) 
    		new ResponseEntity<>("Please Login!", HttpStatus.UNAUTHORIZED);
    	
    	AddRoomDTO addRoom = new AddRoomDTO();
    	addRoom.setRoom(room);
    	addRoom.setUsername((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    	
    	roomRepo.save(room);
    	
    	Room response = (Room) soap.
    			marshalSendAndReceive(SERVICE_URI, addRoom);
    	
        return new ResponseEntity<>(response.getAdditionalServices(), HttpStatus.OK);
    }
    
    @PostMapping(value = "/addFile")
    public ResponseEntity<?> addFile(@RequestParam(value="file") MultipartFile files,@RequestParam("roomString") Long roomS) throws IOException{
    	
    	if(SecurityContextHolder.getContext().getAuthentication() == null) 
    		new ResponseEntity<>("Please Login!", HttpStatus.UNAUTHORIZED);
    	
    	System.out.println(files.getOriginalFilename());
    	Image im = new Image();
    	im.setImage(files.getBytes());
    	im.setNameForImage(files.getOriginalFilename());
    	im.setRoomId(roomS);
    	
     	Image i = (Image) soap.
    			marshalSendAndReceive(SERVICE_URI, im);
     	
		return new ResponseEntity<>("Successfully uploaded file! ",HttpStatus.OK);
		
    }

    @PostMapping(value = "/reserveRoom/")
    public ResponseEntity<?> reserveRoom(@RequestBody Reservation reservation, @QueryParam(value = "idRoom") Long idRoom){
    	if(SecurityContextHolder.getContext().getAuthentication() == null) 
    		new ResponseEntity<>("Please Login!", HttpStatus.UNAUTHORIZED);
    	
    	System.out.println("fsafas");
    	System.out.println(idRoom);
    	Room room = roomRepo.getOne(idRoom);
    	room.getReservation().add(reservation);
    	
    	roomRepo.saveAndFlush(room);
    	//treba i korisniku upisati na glavnom frontu
    	
        return new ResponseEntity<>("Suceessfully reserved room!", HttpStatus.OK);
    }
    
    @GetMapping(value = "/room/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllRooms(){
    	if(SecurityContextHolder.getContext().getAuthentication() == null) 
    		new ResponseEntity<>("Please Login!", HttpStatus.UNAUTHORIZED);
    	
        return new ResponseEntity<>(roomRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "message/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMessage(){
    	if(SecurityContextHolder.getContext().getAuthentication() == null) 
    		new ResponseEntity<>("Please Login!", HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping(value = "/message/{idUser}")
    public ResponseEntity<?> sendMessage(){
    	if(SecurityContextHolder.getContext().getAuthentication() == null) 
    		new ResponseEntity<>("Please Login!", HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    

    @PostMapping(value = "/reservation/{id}")
    public ResponseEntity<?> confirmReservation(){
    	if(SecurityContextHolder.getContext().getAuthentication() == null) 
    		
    		new ResponseEntity<>("Please Login!", HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginDTO LoginDTO){
    	
    	Collection<? extends GrantedAuthority> gauth;
    	System.out.println("Login:  " + LoginDTO.getUsername());
    	
    	try {
    		UserLoginDTO response = (UserLoginDTO) soap.
    				marshalSendAndReceive(USER_SERVICE_URI, LoginDTO);
    		
    		gauth = AuthorityUtils.commaSeparatedStringToAuthorityList(response.getRole());
    		
    		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					response, response.getUsername(),gauth);
    		
    		SecurityContextHolder.getContext().setAuthentication(authentication);
    		
    		syncWithMainServer();
    		
    		return new ResponseEntity<>(response, HttpStatus.OK);
    	}catch (Exception e) {
			e.printStackTrace();
    		return new ResponseEntity<>("Invalid login!", HttpStatus.NOT_FOUND);
		}
    }
    	
    	public void syncWithMainServer(){
        	
    		
    		System.out.println("udje liii");
    		
    		GetReservationForUserDTO getForUser = new GetReservationForUserDTO();
    		getForUser.setUsername((String) SecurityContextHolder.getContext().getAuthentication().getCredentials());

    		GetRoomsForUserDTO getForUserRoom = new GetRoomsForUserDTO();
    		getForUserRoom.setUsername((String) SecurityContextHolder.getContext().getAuthentication().getCredentials());

    		GetMessagesForUserDTO getForUserMsg= new GetMessagesForUserDTO();
    		getForUserMsg.setUsername((String) SecurityContextHolder.getContext().getAuthentication().getCredentials());

        		
        	GetReservations reservations = (GetReservations) soap.
        			marshalSendAndReceive(SERVICE_URI, getForUser);
        	
        	GetRooms rooms = (GetRooms) soap.
        			marshalSendAndReceive(SERVICE_URI,getForUserRoom);
        	
        	GetMessages msgs  = (GetMessages) soap.
        			marshalSendAndReceive(SERVICE_URI,getForUserMsg);
        	
        	resRepo.saveAll(reservations.getReservation());
        	roomRepo.saveAll(rooms.getRoom());
        	msgRepo.saveAll(msgs.getMessageTable());
        	
 
    }

}
