package com.room.microservice.service;

import com.room.microservice.domain.*;
import com.room.microservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private AgentUserService agentUserService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private PriceListService priceListService;
    @Autowired
    private RoomAdditionalServiceService roomAdditionalServiceService;
    @Autowired
    private AccommodationCategoriesService accommodationCategoriesService;
    @Autowired
    private AccommodationTypeService accommodationTypeService;

    public Room findById(Long id){return roomRepository.getOne(id);}

    public List<Room> findAll(){return roomRepository.findAll();}

    public Room AddRoom(AddRoomDTO addRoomDTO){
        Room r = addRoomDTO.getRoom();

        ListIterator<String> iterator = r.getImage().listIterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
                iterator.set("https://localhost:8043/images/"+next);
        }

        r = roomRepository.save(r);
        AgentUser agentUser = agentUserService.findByUsername(addRoomDTO.getUsername());
        agentUser.getRoom().add(addRoomDTO.getRoom());
        agentUserService.save(agentUser);

        return r;
    }

    public List<Room> getRoomsFromAgent(String username){
        List<Room> rooms;

        AgentUser agentUser = agentUserService.findByUsername(username);
        rooms = agentUser.getRoom();

        return rooms;
    }

    public List<Room> getAllRooms(){return roomRepository.findAll();}


    public PriceList getPriceListForRoom(ClientReservationDTO clientReservationDTO) {
        Room room = findById(clientReservationDTO.getRoomId());

        for(PriceList pl: room.getPriceList()){
            if(havePriceListForDate(pl.getMonth(),clientReservationDTO.getCheckIn()))
                return pl;
        }

        return null;
    }

    public boolean havePriceListForDate(Date date1, Date date2) {
        boolean ret = true;
        SimpleDateFormat x = new SimpleDateFormat("yyyy.MM.dd.");

        String date1String[] = x.format(date1).split("\\.");
        String date2String[] = x.format(date2).split("\\.");

        int date1year = Integer.parseInt(date1String[0]);
        int date2year = Integer.parseInt(date2String[0]);

        if (date1year != date2year)
            return false;

        int date1month = Integer.parseInt(date1String[1]);
        int date2month = Integer.parseInt(date2String[1]);

        if (date1month != date2month)
            return false;


        return ret;
    }

}
