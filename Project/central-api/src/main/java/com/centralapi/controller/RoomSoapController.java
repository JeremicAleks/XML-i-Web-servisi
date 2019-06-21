package com.centralapi.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import com.centralapi.service.RoomService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.centralapi.domain.xml.xml_ftn.reservation.ReservationDTO;
import com.centralapi.domain.xml.xml_ftn.rooms.AddRoomDTO;
import com.centralapi.domain.xml.xml_ftn.rooms.GetAccommodationCategories;
import com.centralapi.domain.xml.xml_ftn.rooms.GetAccommodationTypes;
import com.centralapi.domain.xml.xml_ftn.rooms.GetAdditionalServices;
import com.centralapi.domain.xml.xml_ftn.rooms.GetCategories;
import com.centralapi.domain.xml.xml_ftn.rooms.GetRoomAdditionalServices;
import com.centralapi.domain.xml.xml_ftn.rooms.GetRooms;
import com.centralapi.domain.xml.xml_ftn.rooms.GetTypes;
import com.centralapi.domain.xml.xml_ftn.rooms.Image;
import com.centralapi.domain.xml.xml_ftn.rooms.Room;
import com.centralapi.domain.xml.xml_ftn.users.GetRoomsForUserDTO;
import com.centralapi.exception.ResponseMessage;
import com.centralapi.repo.AccommodationCategoriesRepository;
import com.centralapi.repo.AccommodationTypeRepository;
import com.centralapi.repo.RoomAdditionalServicesRepository;

@Endpoint
public class RoomSoapController {

	private static final String ROOM_NAMESPACE_URI = "http://www.xml-ftn.xml.domain.centralapi.com/Rooms";
	private static final String RESERVATION_NAMESPACE_URI = "http://www.xml-ftn.xml.domain.centralapi.com/Reservation";
	private static final String USER_NAMESPACE_URI = "http://www.xml-ftn.xml.domain.centralapi.com/Users";

	@Autowired
	AccommodationTypeRepository typeRepo;

	@Autowired
	AccommodationCategoriesRepository catRepo;

	@Autowired
	RoomAdditionalServicesRepository addRepo;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RoomService roomService;

	@PreAuthorize("hasRole('ROLE_AGENT_APP')")
	@PayloadRoot(namespace = ROOM_NAMESPACE_URI, localPart = "addRoomDTO")
	@ResponsePayload
	public ResponseEntity<?> addRoom(@RequestPayload AddRoomDTO request) {

		// sacuvati u bazu i vratiti room sa id-om
		Room room = restTemplate.postForObject("http://room-microservice/api/add", request, Room.class);

		return new ResponseEntity<>(room, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_AGENT_APP')")
	@PayloadRoot(namespace = ROOM_NAMESPACE_URI, localPart = "image")
	@ResponsePayload
	public Image addFile(@RequestPayload Image request) throws IOException {

		roomService.roomAddImage(request);

		return request;

	}

	@PreAuthorize("hasRole('ROLE_AGENT_APP')")
	@PayloadRoot(namespace = USER_NAMESPACE_URI, localPart = "GetRoomsForUserDTO")
	@ResponsePayload
	public GetRooms getRooms(@RequestPayload GetRoomsForUserDTO request) throws IOException {

		GetRooms getRooms = restTemplate.postForObject("http://room-microservice/api/all", request,GetRooms.class);

		return  getRooms;

	}

	@PreAuthorize("hasRole('ROLE_AGENT_APP')")
	@PayloadRoot(namespace = ROOM_NAMESPACE_URI, localPart = "GetTypes")
	@ResponsePayload
	public GetAccommodationTypes getCategories(@RequestPayload GetTypes request) throws IOException {

		System.out.println("dsafsa");
		GetAccommodationTypes acc = new GetAccommodationTypes();
		acc.setAccommodationType(typeRepo.findAll());

		return acc;

	}
	@PreAuthorize("hasRole('ROLE_AGENT_APP')")
	@PayloadRoot(namespace = ROOM_NAMESPACE_URI, localPart = "GetCategories")
	@ResponsePayload
	public GetAccommodationCategories getCategories(@RequestPayload GetCategories request) throws IOException {

		System.out.println("dsafsa");
		GetAccommodationCategories acc = new GetAccommodationCategories();
		acc.setAccommodationCategory(catRepo.findAll());

		return acc;

	}
	
	@PreAuthorize("hasRole('ROLE_AGENT_APP')")
	@PayloadRoot(namespace = ROOM_NAMESPACE_URI, localPart = "GetAdditionalServices")
	@ResponsePayload
	public GetRoomAdditionalServices getCategories(@RequestPayload GetAdditionalServices request) throws IOException {

		System.out.println("dsafsa");
		GetRoomAdditionalServices acc = new GetRoomAdditionalServices();
		acc.setRoomAdditionalService(addRepo.findAll());

		return acc;

	}

	@PayloadRoot(namespace = ROOM_NAMESPACE_URI, localPart = "ReservationDTO")
	@ResponsePayload
	public ResponseMessage roomReservationManagement(@RequestPayload ReservationDTO request) {

		return null;

	}
}
