package com.app.employee;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeApplicationTests {
	
	@Mock
	EmployeeApplication employee;

	@Test
	public void contextLoads() {
	}
	
	/* Testing the main EmployeeApplication class */
	@Test
	   public void main() {
	      EmployeeApplication.main(new String[] {});
	   }

}
