package com.search.microservice.service;

import com.search.microservice.domain.GetRooms;
import com.search.microservice.domain.dto.SearchParamsDTO;

public interface SearchService {

	GetRooms search(SearchParamsDTO spDTO);

}
