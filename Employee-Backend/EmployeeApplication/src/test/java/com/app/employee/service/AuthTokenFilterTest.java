package com.app.employee.service;

import static org.mockito.Mockito.mock;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.employee.EmployeeApplication;
import com.app.employee.model.User;
import com.app.employee.security.jwt.AuthTokenFilter;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = EmployeeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthTokenFilterTest {
	
	@Mock
	AuthTokenFilter authTokenFilter;
	
	@Mock
	private User user;
	
	@Mock
	private FilterChain filterChain;
	
	@BeforeEach
	public void setUp() {
		this.user=new User("Ramyaa","ramyaa@gmail.com","Ramyaa_01");
	}

	/* Testing the authTokenFilter class */
	@Test
	public void doFilterInternalNotValid()throws ServletException, IOException{
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		FilterChain filterChain = mock(FilterChain.class);
		
		this.authTokenFilter.doFilter(request, response, filterChain);
		
	}
}
