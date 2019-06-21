package com.room.microservice.service;

import com.room.microservice.domain.AgentUser;
import com.room.microservice.domain.GetRoomsForUserDTO;
import com.room.microservice.domain.Room;
import com.room.microservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private AgentUserService agentUserService;

    public Room findById(Long id){return roomRepository.getOne(id);}

    public List<Room> findAll(){return roomRepository.findAll();}

    public Room AddRoom(Room r, AgentUser agentUser){

        List<Room> agentRooms = agentUser.getRoom();
        Room room = roomRepository.save(r);
        agentRooms.add(room);
        agentUserService.save(agentUser);

        return room;
    }

    public List<Room> getRoomsFromAgent(GetRoomsForUserDTO getRoomsForUserDTO){
        List<Room> rooms = new ArrayList<>();

        AgentUser agentUser = agentUserService.findByUsername(getRoomsForUserDTO.getUsername());
        rooms = agentUser.getRoom();

        return rooms;
    }









}
