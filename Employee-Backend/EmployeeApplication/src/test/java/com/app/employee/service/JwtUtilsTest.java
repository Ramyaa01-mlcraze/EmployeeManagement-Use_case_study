package com.app.employee.service;

import static org.junit.Assert.assertThrows;

import java.util.Date;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.employee.EmployeeApplication;
import com.app.employee.model.User;
import com.app.employee.security.jwt.JwtUtils;
import com.app.employee.security.services.UserDetailsImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = EmployeeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JwtUtilsTest {

	@InjectMocks
	JwtUtils jwtUtils;
	
	@Mock
	Authentication authentication;
	
	
	@Mock
	User user;
	
	@Mock
	SignatureException signatureException;
	
	@BeforeEach
	public void setUp() {
		String username="Ramyaa";
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
	}
	
	
	private static final String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
	private static final String jwtSecret="employee";
	private static final int jwtExpirationMs=30000;

	/* Test the JwtUtils class */
	@Test
	public void test()throws Exception{
		
		this.jwtUtils.validateJwtToken(token);
		
		Jwts.builder()
      
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
	}
	
	
}
