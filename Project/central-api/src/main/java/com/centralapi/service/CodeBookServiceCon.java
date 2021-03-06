package com.centralapi.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centralapi.domain.dto.AccommodationDTO;
import com.centralapi.domain.xml.xml_ftn.rooms.AccommodationCategory;
import com.centralapi.domain.xml.xml_ftn.rooms.AccommodationType;
import com.centralapi.domain.xml.xml_ftn.rooms.RoomAdditionalService;
import com.centralapi.exception.GlobalException;
import com.centralapi.exception.ResponseMessage;
import com.centralapi.repo.AccommodationCategoriesRepository;
import com.centralapi.repo.AccommodationTypeRepository;
import com.centralapi.repo.RoomAdditionalServicesRepository;

@Service
public class CodeBookServiceCon implements CodeBookService {

	@Autowired
	AccommodationTypeRepository accommTypeRepo;
	
	@Autowired
	AccommodationCategoriesRepository accommCategoryRepo;
	
	@Autowired
	RoomAdditionalServicesRepository roomAdditionalServRepo;
	
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

		accommTypeRepo.save(new AccommodationType(accommodationType, true));

		return new ResponseMessage("Successfully added accommodation type!");
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

	@Override
	public ResponseMessage updateAccommodationType(@Valid AccommodationDTO accommodationType) {

		Optional<AccommodationType> at = accommTypeRepo.findById(accommodationType.getId());
		if ( at == null) {
			throw new GlobalException("Accommodation type doesn't exist!");
		}
		
		at.get().setDescription(accommodationType.getDescription());
		accommTypeRepo.saveAndFlush(at.get());

		return new ResponseMessage("Successfully updated accommodation type!");
	}

	@Override
	public List<AccommodationCategory> getAllAccomodationCategories() {
		return accommCategoryRepo.findAll();
	}

	@Override
	public ResponseMessage addAccommodationCategory(String accommodationCategory) {

		if (accommTypeRepo.findByDescription(accommodationCategory) != null) {
			throw new GlobalException("Accommodation category alredy exist!");
		}
		
		System.out.println(accommodationCategory);

		accommCategoryRepo.save(new AccommodationCategory(accommodationCategory, true));

		return new ResponseMessage("Successfully added accommodation category!");
	}

	@Override
	public ResponseMessage deleteAccommodationCategory(Long id) {
		
		Optional<AccommodationCategory> ac = accommCategoryRepo.findById(id);
		if ( ac == null) {
			throw new GlobalException("Accommodation category doesn't exist!");
		}
		
		ac.get().setActive(false);
		accommCategoryRepo.saveAndFlush(ac.get());

		return new ResponseMessage("Successfully deleted accommodation category!");
	}

	@Override
	public ResponseMessage updateAccommodationCategory(@Valid AccommodationDTO accommodationCategory) {

		Optional<AccommodationCategory> ac = accommCategoryRepo.findById(accommodationCategory.getId());
		if ( ac == null) {
			throw new GlobalException("Accommodation category doesn't exist!");
		}
		
		ac.get().setDescription(accommodationCategory.getDescription());
		accommCategoryRepo.saveAndFlush(ac.get());

		return new ResponseMessage("Successfully updated accommodation category!");
	}

	@Override
	public List<RoomAdditionalService> getAllAdditionalServices() {
		return roomAdditionalServRepo.findAll();
	}

	@Override
	public ResponseMessage addAdditionalService(String additionalService) {

		if (roomAdditionalServRepo.findByDescription(additionalService) != null) {
			throw new GlobalException("Additional service alredy exist!");
		}
		
		System.out.println(additionalService);

		roomAdditionalServRepo.save(new RoomAdditionalService(additionalService, true));

		return new ResponseMessage("Successfully added additional service!");
	}

	@Override
	public ResponseMessage deleteAdditionalService(Long id) {

		Optional<RoomAdditionalService> ac = roomAdditionalServRepo.findById(id);
		if ( ac == null) {
			throw new GlobalException("Additional service doesn't exist!");
		}
		
		ac.get().setActive(false);
		roomAdditionalServRepo.saveAndFlush(ac.get());

		return new ResponseMessage("Successfully deleted additional service!");
	}

	@Override
	public ResponseMessage updateAdditionalService(@Valid AccommodationDTO additionalService) {

		Optional<RoomAdditionalService> ac = roomAdditionalServRepo.findById(additionalService.getId());
		if ( ac == null) {
			throw new GlobalException("Additional service doesn't exist!");
		}
		
		ac.get().setDescription(additionalService.getDescription());
		roomAdditionalServRepo.saveAndFlush(ac.get());

		return new ResponseMessage("Successfully updated additional service!");
	}
}
