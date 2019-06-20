package com.room.microservice.service;

import com.room.microservice.domain.AgentUser;
import com.room.microservice.domain.DeleteRoom;
import com.room.microservice.domain.Room;
import com.room.microservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        r.setAgentUser(agentUser);

        return roomRepository.save(r);
    }

    public boolean DeleteRoom(DeleteRoom deleteRoom){
        AgentUser agentUser = agentUserService.findByUsername(deleteRoom.getUsername());
        Room room = findById(deleteRoom.getRoomId());

        if(room.getAgentUser() == agentUser){
            roomRepository.delete(room);
            return true;
        }


        return false;
    }







}
