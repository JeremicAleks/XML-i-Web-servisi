package com.room.microservice.service;

import com.room.microservice.domain.Location;
import com.room.microservice.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location findById(Long id){return locationRepository.getOne(id);}

    public List<Location> findAll(){return locationRepository.findAll();}

    public Location save(Location location){return  locationRepository.save(location);}

}
