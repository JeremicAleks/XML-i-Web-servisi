package com.agentapi.controller;

import java.io.IOException;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.agentapi.com.centralapi.domain.xml.xml_ftn.reservation.AllowReservationDTO;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.reservation.Reservation;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.AddRoomDTO;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.GetAccommodationCategories;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.GetAccommodationTypes;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.GetAdditionalServices;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.GetCategories;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.GetRoomAdditionalServices;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.GetRooms;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.GetTypes;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.Image;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.Room;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.users.GetMessagesForUserDTO;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.users.GetReservationForUserDTO;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.users.GetRoomsForUserDTO;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.users.LoginDTO;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.users.SendMessageDTO;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.users.UserInfo;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.users.UserLoginDTO;
import com.agentapi.domain.ResponseMessage;
import com.agentapi.repo.AccommodationCategoriesRepository;
import com.agentapi.repo.AccommodationTypeRepository;
import com.agentapi.repo.MessageRepository;
import com.agentapi.repo.ReservationRepository;
import com.agentapi.repo.RoomAdditionalServicesRepository;
import com.agentapi.repo.RoomRepository;
import com.agentapi.repo.UserInfoRepo;
import com.agentapi.service.MessageService;
import com.agentapi.service.ReservationService;

@RestController
@RequestMapping("/api")
public class AgentController {

	static final String SERVICE_URI = "https://localhost:8043/ws/";
	static final String USER_SERVICE_URI = "https://localhost:8043/ws/";

	@Autowired
	UserInfoRepo userInfo;

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	AccommodationTypeRepository typeRepo;

	@Autowired
	AccommodationCategoriesRepository catRepo;

	@Autowired
	RoomAdditionalServicesRepository addRepo;

	@Autowired
	ReservationRepository resRepo;

	@Autowired
	MessageRepository msgRepo;

	@Autowired
	RoomRepository roomRepo;

	@Autowired
	MessageService msgService;

	@Autowired
	ReservationService resService;

	@Autowired
	WebServiceTemplate soap;

	@PostMapping(value = "/addRoom")
	public ResponseEntity<?> addRoom(@RequestBody Room room) {

		if (userInfo.count() == 1) {
			
			AddRoomDTO addRoom = new AddRoomDTO();
			addRoom.setRoom(room);
			addRoom.setUsername(userInfo.findAll().get(0).getUsername());
			System.out.println("Room addd service : " + room.getRoomAdditionalService().size());

			Room response = (Room) soap.marshalSendAndReceive(SERVICE_URI, addRoom);
			roomRepo.save(response);
			return new ResponseEntity<>(new ResponseMessage("Successfully added room!"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage("Please Login!"), HttpStatus.UNAUTHORIZED);
	}

	@PostMapping(value = "/addFile")
	public ResponseEntity<?> addFile(@RequestParam(value = "file") MultipartFile files,
			@RequestParam("roomString") Long roomS) throws IOException {

		if (userInfo.count() != 1)
			new ResponseEntity<>(new ResponseMessage("Please Login!"), HttpStatus.UNAUTHORIZED);

		Image im = new Image();
		im.setImage(files.getBytes());
		im.setNameForImage(files.getOriginalFilename());
		im.setRoomId(roomS);

		Image i = (Image) soap.marshalSendAndReceive(SERVICE_URI, im);

		return new ResponseEntity<>(new ResponseMessage("Successfully uploaded file!"), HttpStatus.OK);

	}

	@PostMapping(value = "/reserveRoom/")
	public ResponseEntity<?> reserveRoom(@RequestBody Reservation reservation,
			@QueryParam(value = "idRoom") Long idRoom) {
		if (userInfo.count() != 1)
			return new ResponseEntity<>(new ResponseMessage("Please Login!"), HttpStatus.UNAUTHORIZED);

		Room room = roomRepo.getOne(idRoom);
		room.getReservation().add(reservation);

		roomRepo.saveAndFlush(room);
		// treba i korisniku upisati na glavnom frontu

		return new ResponseEntity<>("Suceessfully reserved room!", HttpStatus.OK);
	}

	@GetMapping(value = "/room/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllRooms() {
		
		if (userInfo.count() == 1)
			return new ResponseEntity<>(roomRepo.findAll(), HttpStatus.OK);

		return new ResponseEntity<>(new ResponseMessage("Please Login!"), HttpStatus.UNAUTHORIZED);

	}
	
	@GetMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> logout() {
		
		if (userInfo.count() == 1) {
			userInfo.deleteAll();
			return new ResponseEntity<>(new ResponseMessage("Successfully logouted!"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage("Please Login!"), HttpStatus.UNAUTHORIZED);

	}
	@GetMapping(value = "/types/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllRoomTypes() {
		System.out.print(userInfo.count());
		if (userInfo.count() == 1)
			return new ResponseEntity<>(typeRepo.findAll(), HttpStatus.OK);

		return new ResponseEntity<>(new ResponseMessage("Please Login!"), HttpStatus.UNAUTHORIZED);

	}
	@GetMapping(value = "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllRoomCategories() {
		System.out.print(userInfo.count());
		if (userInfo.count() == 1)
			return new ResponseEntity<>(catRepo.findAll(), HttpStatus.OK);

		return new ResponseEntity<>(new ResponseMessage("Please Login!"), HttpStatus.UNAUTHORIZED);

	}
	@GetMapping(value = "/roomAdditionalServices/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllRoomAdditionalServices() {
		System.out.print(userInfo.count());
		if (userInfo.count() == 1)
			return new ResponseEntity<>(addRepo.findAll(), HttpStatus.OK);

		return new ResponseEntity<>(new ResponseMessage("Please Login!"), HttpStatus.UNAUTHORIZED);

	}

	@GetMapping(value = "message/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getMessage() {
		if (userInfo.count() != 1)
			return new ResponseEntity<>(new ResponseMessage("Please Login!"), HttpStatus.UNAUTHORIZED);

		return new ResponseEntity<>(msgService.getAllMessages(), HttpStatus.OK);
	}

	@PostMapping(value = "/message")
	public ResponseEntity<?> sendMessage(@RequestBody SendMessageDTO SendMessage) {
		if (userInfo.count() != 1)
			return new ResponseEntity<>(new ResponseMessage("Please Login!"), HttpStatus.UNAUTHORIZED);

		try {

			SendMessage.getMessageTable().setFromUser(userInfo.findAll().get(0).getUsername());
			msgService.addMessageToLocal(SendMessage);

			// treba poslatii na serveru!
			return new ResponseEntity<>(new ResponseMessage("Successfully sended!"), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ResponseMessage("Cant send a message!"), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(value = "/reservation/all")
	public ResponseEntity<?> getReservation() {
		if (userInfo.count() != 1)
			return new ResponseEntity<>(new ResponseMessage("Please Login!"), HttpStatus.UNAUTHORIZED);

		return new ResponseEntity<>(resService.getReservations(), HttpStatus.OK);
	}

	@PostMapping(value = "/reservation")
	public ResponseEntity<?> confirmReservation(@RequestBody AllowReservationDTO allow) {
		if (userInfo.count() != 1)
			return new ResponseEntity<>(new ResponseMessage("Please Login!"), HttpStatus.UNAUTHORIZED);

		try {

			resService.confirmReservation(allow);

			// treba poslatii na serveru!
			return new ResponseEntity<>(resService.getReservations(), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ResponseMessage("Cant allow reservation!"), HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody LoginDTO LoginDTO) {
		try {
			UserLoginDTO response = (UserLoginDTO) soap.marshalSendAndReceive(USER_SERVICE_URI, LoginDTO);

			UserInfo u = new UserInfo(response);
			userInfo.save(u);

			syncWithMainServer();

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ResponseMessage("Invalid login!"), HttpStatus.NOT_FOUND);
		}
	}

	public void syncWithMainServer() {

	
		GetRoomsForUserDTO getForUserRoom = new GetRoomsForUserDTO();
		getForUserRoom.setUsername(userInfo.findAll().get(0).getUsername());

		GetAdditionalServices adServices = new GetAdditionalServices();
		
		GetTypes typeServices = new GetTypes();
		
		GetCategories catServices = new GetCategories();
		
		

		GetRooms rooms = (GetRooms) soap.marshalSendAndReceive(SERVICE_URI, getForUserRoom);

		GetRoomAdditionalServices addS = (GetRoomAdditionalServices) soap.marshalSendAndReceive(SERVICE_URI, adServices);

		GetAccommodationTypes typeS = (GetAccommodationTypes) soap.marshalSendAndReceive(SERVICE_URI, typeServices);

		GetAccommodationCategories catS = (GetAccommodationCategories) soap.marshalSendAndReceive(SERVICE_URI, catServices);

		addRepo.saveAll(addS.getRoomAdditionalService());
		typeRepo.saveAll(typeS.getAccommodationType());
		catRepo.saveAll(catS.getAccommodationCategory());
		roomRepo.saveAll(rooms.getRoom());



	}

}
