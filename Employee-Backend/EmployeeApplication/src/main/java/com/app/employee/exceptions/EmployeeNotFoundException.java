package com.app.employee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*Throws Not Found Exception - 404 status code*/
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends Exception {
	
	public EmployeeNotFoundException(String message) {
		super(message);
	}

}
