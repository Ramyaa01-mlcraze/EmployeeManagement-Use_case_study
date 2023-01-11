package com.app.employee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*Throws Bad Request Exception  - 400 status code*/
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
	
	
	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
