package com.centralapi.service;

import java.util.List;

import javax.validation.Valid;

import com.centralapi.domain.dto.AccommodationDTO;
import com.centralapi.domain.xml.xml_ftn.rooms.AccommodationCategory;
import com.centralapi.domain.xml.xml_ftn.rooms.AccommodationType;
import com.centralapi.domain.xml.xml_ftn.rooms.RoomAdditionalService;
import com.centralapi.exception.ResponseMessage;

public interface CodeBookService {

	List<AccommodationType> getAllAccomodationTypes();

	ResponseMessage addAccommodationType(String accommodationType);

	ResponseMessage deleteAccommodationType(Long id);

	ResponseMessage updateAccommodationType(@Valid AccommodationDTO accommodationType);

	List<AccommodationCategory> getAllAccomodationCategories();
	
	ResponseMessage addAccommodationCategory(String accommodationCategory);

	ResponseMessage deleteAccommodationCategory(Long id);

	ResponseMessage updateAccommodationCategory(@Valid AccommodationDTO accommodationCategory);

	List<RoomAdditionalService> getAllAdditionalServices();

	ResponseMessage addAdditionalService(String additionalService);

	ResponseMessage deleteAdditionalService(Long id);

	ResponseMessage updateAdditionalService(@Valid AccommodationDTO additionalService);

}
