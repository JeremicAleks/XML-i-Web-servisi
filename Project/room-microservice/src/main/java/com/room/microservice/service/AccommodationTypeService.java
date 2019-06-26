package com.room.microservice.service;

import com.room.microservice.domain.AccommodationType;
import com.room.microservice.repository.AccommodationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationTypeService {

    @Autowired
    private AccommodationTypeRepository accommodationTypeRepository;

    public AccommodationType findById(Long id){return accommodationTypeRepository.getOne(id);}

    public List<AccommodationType> findAll(){return accommodationTypeRepository.findAll();}

    public AccommodationType save(AccommodationType accommodationType){return  accommodationTypeRepository.save(accommodationType);}
}
