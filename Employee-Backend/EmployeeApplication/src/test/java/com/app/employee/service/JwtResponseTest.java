package com.app.employee.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.employee.EmployeeApplication;
import com.app.employee.payload.response.JwtResponse;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = EmployeeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JwtResponseTest {

	@InjectMocks
	JwtResponse jwtResponse;
	
	
	public static final String EXPECTED_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
	public static final String EXPECTED_ID="215698";
	public static final String EXPECTED_USERNAME="Ramyaa";
	public static final String EXPECTED_EMAIL="ramyaa@gmail.com";
	public List<String> list = new ArrayList<String>();
	public static final String EXPECTED_TYPE="Bearer";
	

	@BeforeEach
    public void setUp() throws Exception {
		list.add("user");
       jwtResponse = new JwtResponse("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
    		   "215698", "Ramyaa", "ramyaa@gmail.com",list);
    }

    @AfterEach
    public void tearDown() throws Exception {
        System.out.println("Test Completed");

    }

	/* To Test the JwtResponse class */
    @Test
    public void testUserDetails() throws Exception {
       assertEquals(EXPECTED_TOKEN, jwtResponse.getAccessToken());
       assertEquals(EXPECTED_ID, jwtResponse.getId());
       assertEquals(EXPECTED_USERNAME, jwtResponse.getUsername());
       assertEquals(EXPECTED_EMAIL, jwtResponse.getEmail());
       assertEquals(list, jwtResponse.getRoles());
       assertEquals(EXPECTED_TYPE, jwtResponse.getTokenType());

    }
    
    @Test
    public void testSet() throws Exception{
    	jwtResponse.setAccessToken(EXPECTED_TOKEN);
    	jwtResponse.setTokenType(EXPECTED_TYPE);;
    	jwtResponse.setEmail(EXPECTED_EMAIL);
    	jwtResponse.setId(EXPECTED_ID);
    	jwtResponse.setUsername(EXPECTED_USERNAME);
    }

}
