package com.room.microservice.service;

import com.room.microservice.domain.RoomAdditionalService;
import com.room.microservice.repository.RoomAdditionalServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomAdditionalServiceService {

    @Autowired
    RoomAdditionalServiceRepository roomAdditionalServiceRepository;

    public RoomAdditionalService findById(Long id){return roomAdditionalServiceRepository.findById(id).get();}

    public RoomAdditionalService save(RoomAdditionalService roomAdditionalService){return roomAdditionalServiceRepository.save(roomAdditionalService);}

}
