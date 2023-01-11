package com.app.employee.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.employee.EmployeeApplication;
import com.app.employee.security.jwt.AuthEntryPointJwt;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = EmployeeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthEntryPointJwtTest {
	
	
	@Mock
	HttpServletRequest request;
	
	@Mock
	HttpServletResponse response;
	
	@Mock
	AuthenticationException authException;
	
	@InjectMocks 
	private AuthEntryPointJwt authEntryPointJwt;

	/* Testing the commence method in the AuthEntryPointJwt class */
	@Test
	public void commence() throws IOException, ServletException{
		this.authEntryPointJwt.commence(request, response, authException);
	}
}
