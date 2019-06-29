package com.centralapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/protect")
public class TestController {
	
	@PreAuthorize("@securityService.hasProtectedAccess('READ_PRODUCT')")
	@GetMapping(value="/test",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTest() {
		
		
        return new ResponseEntity<>("Its cool!", HttpStatus.OK);
    }

}
