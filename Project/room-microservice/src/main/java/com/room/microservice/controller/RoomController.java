package com.room.microservice.controller;

import com.room.microservice.domain.*;
import com.room.microservice.service.AgentUserService;
import com.room.microservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

        for(Room room : rooms)
            getRooms.getRoom().add(room);


        return getRooms;
    }


}
