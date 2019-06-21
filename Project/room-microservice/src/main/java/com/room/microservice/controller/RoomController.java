package com.room.microservice.controller;

import com.room.microservice.domain.*;
import com.room.microservice.service.AgentUserService;
import com.room.microservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    AgentUserService agentUserService;

    @Autowired
    RoomService roomService;

    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Room addRoom(@RequestBody AddRoomDTO addRoomDTO){
        Room room ;
        AgentUser agentUser = agentUserService.findByUsername(addRoomDTO.getUsername());
         room = roomService.AddRoom(addRoomDTO.getRoom(),agentUser);

        return room;
    }

    @PostMapping(value = "/all",consumes = MediaType.APPLICATION_JSON_VALUE)
    public GetRooms getRooms(@RequestBody GetRoomsForUserDTO getRoomsForUserDTO){
        GetRooms getRooms = new GetRooms();

        getRooms.setRoom(roomService.getRoomsFromAgent(getRoomsForUserDTO));

        return getRooms;
    }

    @PostMapping(value = "/delete",consumes =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteRoom(@RequestBody DeleteRoom deleteRoom){



        return new ResponseEntity<>(HttpStatus.OK);
    }

}
