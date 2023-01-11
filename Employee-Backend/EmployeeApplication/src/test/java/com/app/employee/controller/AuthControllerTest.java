package com.app.employee.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.app.employee.EmployeeApplication;
import com.app.employee.exceptions.UserNotFoundException;
import com.app.employee.model.ERole;
import com.app.employee.model.Employee;
import com.app.employee.model.Role;
import com.app.employee.model.User;
import com.app.employee.payload.request.LoginRequest;
import com.app.employee.payload.request.SignupRequest;
import com.app.employee.payload.response.JwtResponse;
import com.app.employee.payload.response.MessageResponse;
import com.app.employee.repository.RoleRepository;
import com.app.employee.repository.UserRepository;
import com.app.employee.security.jwt.JwtUtils;
import com.app.employee.security.services.UserDetailsImpl;
import com.app.employee.security.services.UserDetailsServiceImpl;
import com.app.employee.service.AuthService;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = EmployeeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@InjectMocks
	private AuthController authController;
	
	@Mock
	private AuthService authService;
	
	@Mock
	private RoleRepository roleRepository;
	
	@Mock
	private UserRepository userRepository;

	
	private MockMvc mockMvc;

    @Mock
    private UserDetailsServiceImpl userDetailsServiceImpl;
    
    @Mock
    private JwtResponse jwtResponse;
    

    @Mock
    private UserDetailsImpl userDetailsImpl;

   
    
    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        MockitoAnnotations.initMocks(this);
       
    }
   
	/* Test for changing the password */
	@Test
    public void changePassword() throws Exception {
		String username ="Ramyaa";
		User user = restTemplate.getForObject(getRootUrl() + "/api/auth/change-password/" + username, User.class);
		user.setPassword("Ramyaa@123");
		restTemplate.put(getRootUrl() + "/api/auth/change-password/" + username, user);
		User updatedUser = restTemplate.getForObject(getRootUrl() + "/api/auth/change-password/" + username, User.class);
		assertNotNull(updatedUser);

    }

	/* Test for the failed login attempts */
	@Test
	public void failedAttempts() throws Exception{
		String username ="Ramyaa";
		User user = restTemplate.getForObject(getRootUrl() + "/api/auth/failed-attempt/" + username, User.class);
		int attempts=2;
		user.setFailedAttempt(++attempts);
		user.setAccountLocked(true);
		user.setLockTime(new Date());
		userRepository.save(user);
		restTemplate.put(getRootUrl() + "/api/auth/failed-attempt/" + username, user);
		User updatedUser = restTemplate.getForObject(getRootUrl() + "/api/auth/failed-attempt/" + username, User.class);
		assertNotNull(updatedUser);
	}

	/* Test for login */
	@Test
	public void login() throws Exception{
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("Ramyaa");
		loginRequest.setPassword("Ramyaa_01");
		
		this.jwtResponse.getId();
		this.jwtResponse.getUsername();
		this.jwtResponse.getEmail();
		this.jwtResponse.getRoles();
		restTemplate.postForEntity(getRootUrl() + "/api/auth/signin", loginRequest, LoginRequest.class);
		User loginUser = restTemplate.getForObject(getRootUrl() + "/api/auth/signin", User.class);
		assertNotNull(loginUser);
	    
	}

	/* Test for register */
	@Test
	public void register() throws Exception{
		SignupRequest signUpRequest = new SignupRequest();
		signUpRequest.setUsername("Ramyaa");
		signUpRequest.setEmail("ramyaa@gmail.com");
		signUpRequest.setPassword("Ramyaa_01");
		Set<String> hash_Set = new HashSet<String>();
		hash_Set.add("user");
		ResponseEntity<?> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/auth/signup", signUpRequest, SignupRequest.class);
		assertNotNull(postResponse);
			
			
	}
	
}
