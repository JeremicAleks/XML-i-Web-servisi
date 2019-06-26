package com.reservation.microservice.service;

import com.reservation.microservice.domain.room.Room;
import com.reservation.microservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room fingById(Long id){return roomRepository.getOne(id);}


    public Room save(Room room) {
        return roomRepository.save(room);
    }
}