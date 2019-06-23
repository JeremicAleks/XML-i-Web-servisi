package com.room.microservice.service;

import com.room.microservice.domain.*;
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
        Room room = new Room();
        room.setDescription(r.getDescription());

        //Prebaciti da ide po id-u
        AccommodationCategory accommodationCategory = new AccommodationCategory();
        accommodationCategory.setDescription(r.getAccommodationCategory().getDescription());
        accommodationCategory = accommodationCategoriesService.save(accommodationCategory);
        room.setAccommodationCategory(accommodationCategory);

        //Prebaciti da ide po id-u

        AccommodationType accommodationType = new AccommodationType();
        accommodationType.setDescription(r.getAccommodationType().getDescription());
        accommodationType = accommodationTypeService.save(accommodationType);
        room.setAccommodationType(accommodationType);
        room.setDaysForCancel(r.getDaysForCancel());
        Location loc = new Location();
        loc.setLat(r.getLocation().getLat());
        loc.setLng(r.getLocation().getLng());
        loc.setName(r.getLocation().getName());
        loc = locationService.save(loc);
        room.setLocation(loc);
        room.setNumberOfBeds(r.getNumberOfBeds());


        List<PriceList> priceLists = r.getPriceList();
        for (PriceList priceList: priceLists){
            PriceList pL = new PriceList();
            pL.setMonth(priceList.getMonth());
            pL.setPrice(priceList.getPrice());
            pL = priceListService.save(pL);
            room.getPriceList().add(pL);
        }

        // getAdditionalService je prazno...
        List<RoomAdditionalService> roomAdditionalServices = r.getRoomAdditionalService();

        //Prebaciti da ide po id-u

        for(RoomAdditionalService rAD : roomAdditionalServices){
            RoomAdditionalService roomAdditionalService = new RoomAdditionalService();
            roomAdditionalService.setDescription(rAD.getDescription());
            roomAdditionalService = roomAdditionalServiceService.save(roomAdditionalService);
            room.getRoomAdditionalService().add(roomAdditionalService);
        }

        room = roomRepository.save(room);
        AgentUser agentUser = agentUserService.findByUsername(addRoomDTO.getUsername());
        List<Room> agentRooms = agentUser.getRoom();
        agentRooms.add(room);
        agentUserService.save(agentUser);

        return room;
    }

    public List<Room> getRoomsFromAgent(String username){
        List<Room> rooms;

        AgentUser agentUser = agentUserService.findByUsername(username);
        rooms = agentUser.getRoom();

        return rooms;
    }

    public List<Room> getAllRooms(){return roomRepository.findAll();}









}
