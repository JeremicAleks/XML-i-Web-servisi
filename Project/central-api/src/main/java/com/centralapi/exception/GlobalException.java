package com.centralapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class GlobalException extends RuntimeException {



	private static final long serialVersionUID = 7585382983410067115L;



	public GlobalException(String message) {

		super(message);

	}



}