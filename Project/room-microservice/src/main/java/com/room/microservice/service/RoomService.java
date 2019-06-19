package com.room.microservice.service;

import com.room.microservice.domain.AgentUser;
import com.room.microservice.domain.Room;
import com.room.microservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room findById(Long id){return roomRepository.getOne(id);}

    public List<Room> findAll(){return roomRepository.findAll();}

    public Room AddRoom(Room r, AgentUser agentUser){

        r.setAgentUser(agentUser);

        return roomRepository.save(r);
    }







}
