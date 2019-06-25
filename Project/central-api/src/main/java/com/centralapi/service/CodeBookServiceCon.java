package com.centralapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centralapi.domain.xml.xml_ftn.rooms.AccommodationType;
import com.centralapi.domain.xml.xml_ftn.users.Role;
import com.centralapi.exception.GlobalException;
import com.centralapi.exception.ResponseMessage;
import com.centralapi.repo.AccommodationTypeRepository;

@Service
public class CodeBookServiceCon implements CodeBookService {

	@Autowired
	AccommodationTypeRepository accommTypeRepo;
	
	@Override
	public List<AccommodationType> getAllAccomodationTypes() { 
		return accommTypeRepo.findAll();
	}

	@Override
	public ResponseMessage addAccommodationType(String accommodationType) {

		if (accommTypeRepo.findByDescription(accommodationType) != null) {
			throw new GlobalException("Accommodation type alredy exist!");
		}
		
		System.out.println(accommodationType);

		accommTypeRepo.save(new AccommodationType(accommodationType));

		return new ResponseMessage("Successfully add accommodation type!");
	}

	@Override
	public ResponseMessage deleteAccommodationType(Long id) {
		
		Optional<AccommodationType> at = accommTypeRepo.findById(id);
		if ( at == null) {
			throw new GlobalException("Accommodation type doesn't exist!");
		}
		
		at.get().setActive(false);
		accommTypeRepo.saveAndFlush(at.get());

		return new ResponseMessage("Successfully deleted accommodation type!");
	}

}
