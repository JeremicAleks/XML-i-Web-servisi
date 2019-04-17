package com.authorizationapi.controller;

import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.authorizationapi.domain.User;
import com.authorizationapi.domain.acl.AclObjectIdentity;
import com.authorizationapi.domain.acl.AclSid;
import com.authorizationapi.repo.AclEntryRepository;
import com.authorizationapi.repo.AclObjectRepository;
import com.authorizationapi.repo.AclSidRepository;
import com.authorizationapi.repo.UserRepository;
import com.authorizationapi.service.AclService;
import com.authorizationapi.utils.TokenUtils;
/**
 * Na cemu sam ja gubio vreme :D:D:D
 * @author Kiriyaga
 *
 */
@Controller
@CrossOrigin
@RequestMapping("/api")
public class AclController {
	
	@Autowired
	TokenUtils tokenUtils;
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AclService aclService;
	
	@Autowired
	AclSidRepository aclSidRepo;
	
	@Autowired
	AclObjectRepository aclObjRepo;
	
	@Autowired
	AclEntryRepository aclEntryRepo;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/findFiles")
	public ResponseEntity<?> findFiles(@RequestHeader("Token-Authority") String token) {
		
		User user = userRepo.findByUsername(tokenUtils.getUsernameFromToken(token));
		if(user==null) {
			return new ResponseEntity<>("User doenst exist!", HttpStatus.NOT_FOUND);
		}
		if(tokenUtils.validateToken(token, user)== true) {
			List<Path> list = aclService.findFiles(user);
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Token is not valid!", HttpStatus.NOT_FOUND);
		

	}

	
	@RequestMapping(method = RequestMethod.GET, value = "/getFiles")
	public ResponseEntity<?> getFiles() {
		
		
		return new ResponseEntity<>(aclService.getAllFiles(), HttpStatus.OK);
		

	}

}
