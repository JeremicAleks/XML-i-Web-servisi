package com.centralapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centralapi.domain.dto.AccommodationDTO;
import com.centralapi.domain.xml.xml_ftn.rooms.AccommodationCategory;
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
	
	@PostMapping(value = "/accommodation/deleteType", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> deleteAccommodationType(@Valid @RequestBody AccommodationDTO accommodationType) {
		
		System.out.println("deleteid: " + accommodationType.getId());
		ResponseMessage rm = codeBookService.deleteAccommodationType(accommodationType.getId());

		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	
	@PutMapping(value = "/accommodation/updateType", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> updateAccommodationType(@Valid @RequestBody AccommodationDTO accommodationType) {
		
		System.out.println("updateid: " + accommodationType.getId());
		ResponseMessage rm = codeBookService.updateAccommodationType(accommodationType);

		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	
	@GetMapping(value = "/accommodation/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllAccommodationCategories(){
		
		List<AccommodationCategory> results = codeBookService.getAllAccomodationCategories();
		
		if (results == null) {
			System.out.println("vraca null umesto accommCategories");
		}

        return new ResponseEntity<>(results, HttpStatus.OK);
    }
	
	@PostMapping(value = "/accommodation/newCategory", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> addAccommodationCategory(@Valid @RequestBody AccommodationDTO accommodationCategory) {
		
		ResponseMessage rm = codeBookService.addAccommodationCategory(accommodationCategory.getDescription());

		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	
	@PostMapping(value = "/accommodation/deleteCategory", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> deleteAccommodationCategory(@Valid @RequestBody AccommodationDTO accommodationCategory) {
		
		System.out.println("deleteid: " + accommodationCategory.getId());
		ResponseMessage rm = codeBookService.deleteAccommodationCategory(accommodationCategory.getId());

		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	
	@PutMapping(value = "/accommodation/updateCategory", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> updateAccommodationCategory(@Valid @RequestBody AccommodationDTO accommodationCategory) {
		
		System.out.println("updateid: " + accommodationCategory.getId());
		ResponseMessage rm = codeBookService.updateAccommodationCategory(accommodationCategory);

		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	
	@GetMapping(value = "/accommodation/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllAdditionalServices(){
		
		List<AccommodationCategory> results = codeBookService.getAllAccomodationCategories();
		
		if (results == null) {
			System.out.println("vraca null umesto accommCategories");
		}

        return new ResponseEntity<>(results, HttpStatus.OK);
    }
	
	@PostMapping(value = "/accommodation/newCategory", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> addAdditionalService(@Valid @RequestBody AccommodationDTO accommodationCategory) {
		
		ResponseMessage rm = codeBookService.addAccommodationCategory(accommodationCategory.getDescription());

		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	
	@PostMapping(value = "/accommodation/deleteCategory", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> deleteAdditionalService(@Valid @RequestBody AccommodationDTO accommodationCategory) {
		
		System.out.println("deleteid: " + accommodationCategory.getId());
		ResponseMessage rm = codeBookService.deleteAccommodationCategory(accommodationCategory.getId());

		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	
	@PutMapping(value = "/accommodation/updateCategory", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> updateAdditionalService(@Valid @RequestBody AccommodationDTO accommodationCategory) {
		
		System.out.println("updateid: " + accommodationCategory.getId());
		ResponseMessage rm = codeBookService.updateAccommodationCategory(accommodationCategory);

		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	
	
}
