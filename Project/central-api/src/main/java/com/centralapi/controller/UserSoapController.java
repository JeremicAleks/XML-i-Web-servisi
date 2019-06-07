package com.centralapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.centralapi.domain.User;
import com.centralapi.domain.xml.xml_ftn.rooms.AddRoom;
import com.centralapi.domain.xml.xml_ftn.rooms.DeleteRoom;
import com.centralapi.domain.xml.xml_ftn.users.AgentUser;
import com.centralapi.domain.xml.xml_ftn.users.GetUserRequest;
import com.centralapi.domain.xml.xml_ftn.users.RegistredUser;
import com.centralapi.domain.xml.xml_ftn.users.UserLogin;
import com.centralapi.exception.ResponseMessage;
import com.centralapi.repo.UserRepository;

@Endpoint
public class UserSoapController {
	
	private static final String USER_NAMESPACE_URI = "http://www.xml-ftn.xml.domain.centralapi.com/Users";
	private static final String ROOM_NAMESPACE_URI = "http://www.xml-ftn.xml.domain.centralapi.com/Rooms";
	
	
	@Autowired
	private UserRepository userRepo;
	
    @PayloadRoot(namespace = USER_NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public User getUser(@RequestPayload GetUserRequest  request) {
    	User response = userRepo.findByUsername(request.getUsername());

        return response;
    }
    
    @PayloadRoot(namespace = USER_NAMESPACE_URI, localPart = "userLogin")
    @ResponsePayload
    public AgentUser login(@RequestPayload UserLogin  request) {
    	
    	return null;
        
    }
    
    @PayloadRoot(namespace = ROOM_NAMESPACE_URI, localPart = "addRoom")
    @ResponsePayload
    public ResponseMessage addRoom(@RequestPayload AddRoom  request) {
    	
    	return null;
        
    }
    
    @PayloadRoot(namespace = ROOM_NAMESPACE_URI, localPart = "addRoom")
    @ResponsePayload
    public ResponseMessage editRoom(@RequestPayload AddRoom  request) {
    	
    	return null;
        
    }
    @PayloadRoot(namespace = ROOM_NAMESPACE_URI, localPart = "deleteRoom")
    @ResponsePayload
    public ResponseMessage deleteRoom(@RequestPayload DeleteRoom  request) {
    	
    	return null;
        
    }

}
