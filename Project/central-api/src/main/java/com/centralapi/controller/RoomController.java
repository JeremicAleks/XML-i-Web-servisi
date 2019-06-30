package com.centralapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.centralapi.domain.dto.ClientReservationDTO;
import com.centralapi.domain.dto.ShowRoomDTO;
import com.centralapi.domain.xml.xml_ftn.rooms.GetRooms;
import com.centralapi.domain.xml.xml_ftn.rooms.PriceList;
import com.centralapi.domain.xml.xml_ftn.rooms.RateAndComment;
import com.centralapi.domain.xml.xml_ftn.rooms.Room;
import com.centralapi.repo.AccommodationCategoriesRepository;
import com.centralapi.repo.AccommodationTypeRepository;
import com.centralapi.repo.RoomAdditionalServicesRepository;

@RestController
@RequestMapping("api/room")
public class RoomController {

	@Autowired
	RestTemplate rest;
	
	@Autowired
	RoomAdditionalServicesRepository addRepo;
	
	@Autowired
	AccommodationTypeRepository typeRepo;
	
	@Autowired
	AccommodationCategoriesRepository catRepo;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRooms(){

        GetRooms getRooms = restTemplate.getForObject("https://room-microservice/api/all",GetRooms.class);

        return new ResponseEntity<>(getRooms.getRoom(), HttpStatus.OK);
    }
    
    @PostMapping(value = "/getRoomForShow",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRoomForShow(@RequestBody ClientReservationDTO client){

        RestTemplate rt = new RestTemplate();
    	
        ShowRoomDTO room = new ShowRoomDTO();
        Room r = restTemplate.getForObject("https://room-microservice/api/"+client.getRoomId(),Room.class);
		ResponseEntity<List<RateAndComment>> response = rt.exchange("http://ec2-35-181-44-209.eu-west-3.compute.amazonaws.com:8048/api/rates/all/"+client.getRoomId(),
				HttpMethod.GET, null, new ParameterizedTypeReference<List<RateAndComment>>() {
				});
		PriceList priceList = restTemplate.postForObject("https://room-microservice/api/getPriceList", client, PriceList.class);
		room.setPriceList(priceList);
        room.setRoom(r);
        int days= (int) ((client.getCheckOut().getTime() - client.getCheckIn().getTime())/ (1000*60*60*24));

        room.setTotalDays(Integer.toString(days));
        room.setRatesAndComments(response.getBody());

        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRoom(@PathVariable Long id){

        Room room = restTemplate.getForObject("https://room-microservice/api/"+id,Room.class);


        return new ResponseEntity<>(room, HttpStatus.OK);
    }
    
    @GetMapping(value = "/types",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRoomTypes(){
    	System.out.println("dsada");
        return new ResponseEntity<>(typeRepo.findAll(), HttpStatus.OK);
    }
    
    @GetMapping(value = "/categories",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRoomCategories(){

        return new ResponseEntity<>(catRepo.findAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/additionalServices",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRoomAdditionalService(){

        return new ResponseEntity<>(addRepo.findAll(), HttpStatus.OK);
    }
    
	@GetMapping(value="/test",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> testsearch() {
		System.out.println("fsafsafa");
		ResponseEntity<String> temp = rest.exchange("https://room-microservice/api/test", HttpMethod.GET, null, String.class);
		return new ResponseEntity<>(temp.getBody(), HttpStatus.OK);

    }
}
