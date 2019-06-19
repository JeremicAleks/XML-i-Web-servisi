package com.room.microservice.controller;


import com.room.microservice.domain.AddRoomDTO;
import com.room.microservice.domain.AgentUser;
import com.room.microservice.domain.Room;
import com.room.microservice.service.AgentUserService;
import com.room.microservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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


}
