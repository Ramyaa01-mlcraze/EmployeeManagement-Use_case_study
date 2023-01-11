package com.app.employee.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.employee.EmployeeApplication;
import com.app.employee.exceptions.UserNotFoundException;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = EmployeeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserNotFoundExceptionTest {
	
	@InjectMocks
	UserNotFoundException userNotFoundException;

	/* To Test the User not found Exception */
	@Test
	public void test() {
		this.userNotFoundException.toString();
		this.userNotFoundException.getMessage();
	}
}
