package com.room.microservice.service;

import com.room.microservice.domain.AccommodationCategory;
import com.room.microservice.repository.AccommodationCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationCategoriesService {

    @Autowired
    private AccommodationCategoriesRepository accommodationCategoriesRepository;

    public AccommodationCategory findById(Long id){return accommodationCategoriesRepository.getOne(id);}

    public List<AccommodationCategory> findAll(){return accommodationCategoriesRepository.findAll();}

    public AccommodationCategory save(AccommodationCategory accommodationCategory){return  accommodationCategoriesRepository.save(accommodationCategory);}
}
