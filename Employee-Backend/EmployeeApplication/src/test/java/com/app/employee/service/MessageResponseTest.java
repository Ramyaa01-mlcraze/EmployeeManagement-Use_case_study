package com.app.employee.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.employee.EmployeeApplication;
import com.app.employee.payload.response.MessageResponse;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = EmployeeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageResponseTest {

	@InjectMocks
	private MessageResponse messageResponse;
	
	private static final String MESSAGE="User created";

	/* To Test the MessageResponse */
	@Test
	public void test() {
		this.messageResponse.getMessage();
		this.messageResponse.setMessage(MESSAGE);
	}
}
