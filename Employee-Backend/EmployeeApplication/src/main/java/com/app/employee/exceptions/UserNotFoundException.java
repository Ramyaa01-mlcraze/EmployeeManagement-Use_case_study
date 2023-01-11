package com.app.employee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*Throws user not found exception - 404 status code*/
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception {
	public UserNotFoundException(String message) {
		super(message);
	}

}

