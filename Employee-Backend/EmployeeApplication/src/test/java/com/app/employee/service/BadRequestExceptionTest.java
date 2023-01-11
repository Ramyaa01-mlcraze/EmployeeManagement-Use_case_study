package com.app.employee.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.employee.EmployeeApplication;
import com.app.employee.exceptions.BadRequestException;
import com.app.employee.model.User;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = EmployeeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BadRequestExceptionTest {
	
	@InjectMocks
	private BadRequestException badRequestException;
	
	/* Testing the Exception class */
	@Test
	public void testException() {
		this.badRequestException.getMessage();
	}

	/* Testing the Exception message */ 
	@Test
	public void testExceptionMessage() {
		this.badRequestException.getLocalizedMessage();
		this.badRequestException.toString();
	}
}
