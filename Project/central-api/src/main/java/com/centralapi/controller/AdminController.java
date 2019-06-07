package com.centralapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin")
public class AdminController {


    @GetMapping(value = "/comment/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllComments(){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping(value = "/comment/{id}")
    public ResponseEntity<?> publishComment(){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping(value="/user/block/{id}")
    public ResponseEntity<?> blockUser(){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping(value = "/user/activate/{id}")
    public ResponseEntity<?> activateUser(){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping(value = "/user/delete/{id}")
    public ResponseEntity<?> deleteUser(){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping(value = "agent/add",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAgent(){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }




}
