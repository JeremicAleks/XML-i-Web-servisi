package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.RateAndComment;
import com.example.demo.domain.dto.AddRateAndCommentDTO;
import com.example.demo.repo.RateAndCommentRepository;
import com.example.demo.service.RateAndCommentService;

@RestController
@RequestMapping("/api")
public class RateAndCommentController {
	
	@Autowired
	RateAndCommentService rateAndComment;
	
	@Autowired
	RateAndCommentRepository ratesRepo;
	
	@GetMapping(value = "/rates/all/{idRoom}")
	public ResponseEntity<?> getAllRatesForRoom(@PathVariable Long idRoom) {
		
		System.out.println(idRoom);
		List<RateAndComment> rates = rateAndComment.getRatesForRoom(idRoom);
		if(rates == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(rates, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/rates/all")
	public ResponseEntity<?> getAllRates() {
		
		System.out.println("Nabavka komentara");
		
		return new ResponseEntity<>(ratesRepo.getRatesForAdmin(), HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/rates/add")
	public ResponseEntity<?> addRatesForRoom(@RequestBody AddRateAndCommentDTO rate) {
		
		System.out.println("Dodavanje komentara!");
		RateAndComment rates = rateAndComment.addRateAndComment(rate);
		if(rates == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(rates, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/rates/allow/{id}")
	public ResponseEntity<?> AllowRatesForRoom(@PathVariable Long id) {
		
		try {
		rateAndComment.AllowRateAndComment(id);
		}
		catch (Exception e) {
			return new ResponseEntity<>("Error for allowing rate and comment!", HttpStatus.OK);
		}

		return new ResponseEntity<>("Succussfully allowed rate and comment!", HttpStatus.OK);
		
	}
	

}
