package com.search.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.search.microservice.domain.GetRooms;
import com.search.microservice.domain.dto.SearchParamsDTO;
import com.search.microservice.service.SearchService;

@RestController
@RequestMapping("/api/search")
public class SearchController {
	
	@Autowired
	SearchService searchService;

	@PostMapping(value = "/kurec", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchRoom(@RequestBody SearchParamsDTO spDTO){
		// @RequestBody SearchParamsDTO spDTO

		System.out.println("searchController");
		System.out.println("search for");
		GetRooms searchResults = searchService.search(spDTO);
		
		System.out.println("Nasao je sobe!");
		
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }
}
