package com.search.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.search.microservice.domain.GetRooms;
import com.search.microservice.domain.dto.SearchParamsDTO;
import com.search.microservice.service.SearchService;

@RestController
@RequestMapping("/api/search")
public class SearchController {
	
	@Autowired
	SearchService searchService;

	@PreAuthorize("hasRole('ROLE_CENTRAL_APP')")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchRoom(@RequestBody SearchParamsDTO spDTO){
		// @RequestBody SearchParamsDTO spDTO

		System.out.println("searchController");
		System.out.println("search for");
		GetRooms searchResults = searchService.search(spDTO);
		
		
		System.out.println("[microservice-search] -");
		
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('ROLE_CENTRAL_APP')")
	@GetMapping(value = "/test",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> test(){
		// @RequestBody SearchParamsDTO spDTO

		System.out.println("kklslsjl");
        return new ResponseEntity<>("dsafsafsaf", HttpStatus.OK);
    }
}
