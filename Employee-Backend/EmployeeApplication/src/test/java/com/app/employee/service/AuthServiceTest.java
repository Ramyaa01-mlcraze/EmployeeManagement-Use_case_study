package com.app.employee.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.employee.model.User;
import com.app.employee.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AuthServiceTest {

	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private AuthService authService;

	@Mock
	private User user;
	
	@BeforeEach
	public void setUp() {
		this.user = new User("Ramyaa","ramyaa01@gmail.com","Ramyaa_01");
	}
	
	/* Testing the repository */
	@Test
	public void testRepository() {
		this.authService.existsByUsername("Ramyaa");
		verify(this.userRepository).existsByUsername("Ramyaa");
	}

	/* Testing the save method in the auth service */
	@Test
	public void testRepositoryNew() {
		this.authService.save(user);
		verify(this.userRepository).save(user);
	}
		
}
