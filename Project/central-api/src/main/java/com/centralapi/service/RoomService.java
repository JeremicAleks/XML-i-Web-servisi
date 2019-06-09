package com.centralapi.service;

import com.centralapi.domain.xml.xml_ftn.rooms.Room;
import com.centralapi.repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room findOne(long id){return roomRepository.findById(id).get();}

    public List<Room> findAll(){return roomRepository.findAll();}

    public Page<Room> findAll(Pageable page){return findAll(page);}

    public Room save(Room room){return roomRepository.save(room);}


}
