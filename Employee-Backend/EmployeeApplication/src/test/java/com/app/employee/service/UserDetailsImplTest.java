package com.app.employee.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.employee.EmployeeApplication;
import com.app.employee.model.User;
import com.app.employee.security.services.UserDetailsImpl;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = EmployeeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserDetailsImplTest {

	@InjectMocks
	UserDetailsImpl userDetailsImpl;
	
	@Mock
	User user;

	/* To Test the UserDetailsImpl class */
	@Test
	public void generate() {
		this.userDetailsImpl.equals(user);
		this.userDetailsImpl.getAuthorities();
		this.userDetailsImpl.getEmail();
		this.userDetailsImpl.getId();
		this.userDetailsImpl.getPassword();
		this.userDetailsImpl.getUser();
		this.userDetailsImpl.getUsername();
		this.userDetailsImpl.isAccountNonExpired();
		this.userDetailsImpl.isAccountNonLocked();
		this.userDetailsImpl.isCredentialsNonExpired();
		this.userDetailsImpl.isEnabled();
		this.userDetailsImpl.build(user);
	}
}
