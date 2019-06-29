package com.room.microservice.controller;

import com.room.microservice.domain.*;
import com.room.microservice.service.AgentUserService;
import com.room.microservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    AgentUserService agentUserService;

    @Autowired
    RoomService roomService;

    @PostMapping(value = "/add")
    public Room addRoom(@RequestBody AddRoomDTO addRoomDTO){
        Room room ;

         room = roomService.AddRoom(addRoomDTO);

        return room;
    }

    @GetMapping(value = "/all/{username}")
    public GetRooms getRooms(@PathVariable String username){
        GetRooms getRooms = new GetRooms();

        List<Room> rooms = roomService.getRoomsFromAgent(username);

        for(Room room : rooms)
            getRooms.getRoom().add(room);

        return getRooms;
    }

    @GetMapping(value = "/all")
    public GetRooms getAllRooms(){
        GetRooms getRooms = new GetRooms();

        List<Room> rooms = roomService.getAllRooms();
        System.out.println(rooms.size());

        for(Room room : rooms)
            getRooms.getRoom().add(room);


        return getRooms;
    }

    @PostMapping(value ="/getPriceList")
    public PriceList getPriceList(@RequestBody ClientReservationDTO clientReservationDTO){
        PriceList priceList;

        priceList = roomService.getPriceListForRoom(clientReservationDTO);

        return priceList;
    }

    @GetMapping(value="/{id}")
    public Room getRoom(@PathVariable Long id){
        Room room = roomService.findById(id);
        return room;
    }
    
	@PreAuthorize("hasRole('ROLE_CENTRAL_APP')")
	@GetMapping(value = "/test",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> test(){
		// @RequestBody SearchParamsDTO spDTO

		System.out.println("kklslsjl");
        return new ResponseEntity<>("dsafsafsaf", HttpStatus.OK);
    }



}
