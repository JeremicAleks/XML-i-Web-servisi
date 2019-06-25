package com.centralapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centralapi.domain.xml.xml_ftn.rooms.AccommodationType;
import com.centralapi.service.CodeBookService;

@RestController
@RequestMapping("api")
public class CodeBookController {
	
	@Autowired
	CodeBookService codeBookService;

	@GetMapping(value = "/accommodation/types", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllAccommodationTypes(){
		
		List<AccommodationType> results = codeBookService.getAllAccomodationTypes();
		
		if (results == null) {
			System.out.println("vraca null umesto accommTypes");
		}

        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
