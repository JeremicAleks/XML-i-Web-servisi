package com.centralapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centralapi.domain.dto.AccommodationDTO;
import com.centralapi.domain.dto.RoleDTO;
import com.centralapi.domain.xml.xml_ftn.rooms.AccommodationType;
import com.centralapi.exception.ResponseMessage;
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
	
	@PostMapping(value = "/accommodation/newType", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> addAccommodationType(@Valid @RequestBody AccommodationDTO accommodationType) {
		
		ResponseMessage rm = codeBookService.addAccommodationType(accommodationType.getDescription());

		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/accommodation/deleteType/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> deleteRole(@PathVariable Long id) {
		
		System.out.println("deleteid: " + id);
		ResponseMessage rm = codeBookService.deleteAccommodationType(id);

		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
}
