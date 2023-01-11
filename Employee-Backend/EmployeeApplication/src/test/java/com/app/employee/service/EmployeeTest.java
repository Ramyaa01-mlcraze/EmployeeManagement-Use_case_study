package com.app.employee.service;

import static org.junit.Assert.assertEquals;

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
import com.app.employee.model.Employee;


@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = EmployeeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeTest {

	@InjectMocks
	Employee employee;
	
	public static final long EXPECTED_ID=215698;
	public static final String EXPECTED_FNAME="Ramyaa";
	public static final String EXPECTED_LNAME="Suresh";
	public static final String EXPECTED_EMAIL="ramyaa@gmail.com";
	public static final String EXPECTED_SALARY="60000";
	public static final String EXPECTED_JOB="FSE";
	
	@BeforeEach
    public void setUp() throws Exception {
       employee = new Employee("Ramyaa","Suresh", "ramyaa@gmail.com","60000","FSE");
    }

    @AfterEach
    public void tearDown() throws Exception {
        System.out.println("Test Completed");

    }

	/* Testing the Employee model class */
    @Test
    public void testEmployee()throws Exception{
    	equals(employee.getId());
    	assertEquals(EXPECTED_FNAME,employee.getempFirstName());
    	assertEquals(EXPECTED_LNAME,employee.getempLastName());
    	assertEquals(EXPECTED_EMAIL,employee.getempEmailID());
    	assertEquals(EXPECTED_SALARY,employee.getempSalary());
    	assertEquals(EXPECTED_JOB,employee.getempAllocatedJobs());
    }
    
    @Test
    public void setEmployee()throws Exception{
    	employee.setId(EXPECTED_ID);
    	employee.setempFirstName(EXPECTED_FNAME);
    	employee.setempLastName(EXPECTED_LNAME);
    	employee.setempEmailID(EXPECTED_EMAIL);
    	employee.setempSalary(EXPECTED_SALARY);
    	employee.setempAllocatedJobs(EXPECTED_JOB);
    }
    
    @Test
    public void test()throws Exception{
    	this.employee.toString();
    }
}
