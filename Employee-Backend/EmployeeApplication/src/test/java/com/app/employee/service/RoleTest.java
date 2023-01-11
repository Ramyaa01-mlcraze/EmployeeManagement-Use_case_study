package com.app.employee.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.employee.EmployeeApplication;
import com.app.employee.model.ERole;
import com.app.employee.model.Role;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = EmployeeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleTest {
	
	@InjectMocks
	Role role;
	
	private static final String ID="12";

	private static final ERole ROLE_USER = null;

	/* Test the Role model class */
	@Test
	public void test() throws Exception{
		this.role.getId();
		this.role.getName();
		this.role.setId(ID);
		this.role.setName(ROLE_USER);
	}

}
