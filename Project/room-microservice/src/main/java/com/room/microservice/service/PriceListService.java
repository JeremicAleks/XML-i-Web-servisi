package com.room.microservice.service;

import com.room.microservice.domain.PriceList;
import com.room.microservice.repository.PriceListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceListService {

    @Autowired
    private PriceListRepository priceListRepository;

    public PriceList findById(Long id){return priceListRepository.findById(id).get();}

    public PriceList save(PriceList priceList){return priceListRepository.save(priceList);}



}
