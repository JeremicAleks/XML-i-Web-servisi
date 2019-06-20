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
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    AgentUserService agentUserService;

    @Autowired
    RoomService roomService;

    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Room addRoom(@RequestBody AddRoomDTO addRoomDTO){

        AgentUser agentUser = agentUserService.findByUsername(addRoomDTO.getUsername());
        Room room = roomService.AddRoom(addRoomDTO.getRoom(),agentUser);

        return room;
    }

    @GetMapping(value = "/all")
    public GetRooms getRooms(){
        GetRooms getRooms = new GetRooms();

        List<Room> roomList = roomService.findAll();
        getRooms.setRoom(roomList);

        return getRooms;
    }

    @PostMapping(value = "/delete",consumes =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteRoom(@RequestBody DeleteRoom deleteRoom){

        if(roomService.DeleteRoom(deleteRoom))
        return new ResponseEntity<>(HttpStatus.OK);
        

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
