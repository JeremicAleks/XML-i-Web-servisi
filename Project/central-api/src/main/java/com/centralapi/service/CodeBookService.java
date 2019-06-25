package com.centralapi.service;

import java.util.List;

import com.centralapi.domain.xml.xml_ftn.rooms.AccommodationType;
import com.centralapi.exception.ResponseMessage;

public interface CodeBookService {

	List<AccommodationType> getAllAccomodationTypes();

	ResponseMessage addAccommodationType(String accommodationType);

	ResponseMessage deleteAccommodationType(Long id);

}
