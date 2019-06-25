package com.centralapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centralapi.domain.xml.xml_ftn.rooms.AccommodationType;
import com.centralapi.repo.AccommodationTypeRepository;

@Service
public class CodeBookServiceCon implements CodeBookService {

	@Autowired
	AccommodationTypeRepository accommTypeRepo;
	
	@Override
	public List<AccommodationType> getAllAccomodationTypes() { 
		return accommTypeRepo.findAll();
	}

}
