package com.app.employee.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;

import com.app.employee.EmployeeApplication;
import com.app.employee.download.EmployeeExcelExporter;
import com.app.employee.model.Employee;
import com.app.employee.repository.EmployeeRepository;
import com.app.employee.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	@Mock
	EmployeeService employeeService;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
    private EmployeeController employeeController;
	
	private MockMvc mockMvc;
	
	private String getRootUrl() {
		return "http://localhost:" + port;
	}
	
	@BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }
		
	
	@Test
	public void contextLoads() {
		
	}

	/* Test for retrieving the employees */
	@Test
	public void testGetAllEmployees() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/employees", HttpMethod.GET, entity, String.class);
		assertNotNull(response.getBody());
	}

	/* Test for retrieving the employee by ID */
	@Test
	public void testGetEmployeeById() {
		Employee employee = restTemplate.getForObject(getRootUrl() + "/api/employees/1", Employee.class);
		System.out.println(employee.getempFirstName());
		assertNotNull(employee);
	}

	/* Test for creating an employee */
	@Test
	public void testCreateEmployee() {
		Employee employee = new Employee();
		employee.setempFirstName("Bob");
		employee.setempLastName("Thomas");
		employee.setempEmailID("bob@gmail.com");
		employee.setempSalary("50000");
		employee.setempAllocatedJobs("Programmer");
		
		ResponseEntity<Employee> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/employees", employee, Employee.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	/* Test for updating an employee */
	@Test
	public void testUpdateEmployee() {
		int id=1;
		Employee employee = restTemplate.getForObject(getRootUrl() + "/api/employees/" + id, Employee.class);
		employee.setempFirstName("Allwin");
		employee.setempLastName("Robert");
		restTemplate.put(getRootUrl() + "/api/employees/" + id, employee);
		Employee updatedEmployee = restTemplate.getForObject(getRootUrl() + "/api/employees/" + id, Employee.class);
		assertNotNull(updatedEmployee);
	}

	/* Test for deleting an employee */
	@Test
	public void testDeleteEmployee() {
		int id=2;
		Employee employee = restTemplate.getForObject(getRootUrl() + "/api/employees/" + id, Employee.class);
		assertNotNull(employee);
		
		restTemplate.delete(getRootUrl() + "/api/employees/" + id);
		
		try {
			employee = restTemplate.getForObject(getRootUrl() + "/api/employees/" + id, Employee.class);
		} catch(final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
	
	/* Test for downloading the PDF */
	  @Test
	    public void downloadPdf() throws Exception {
	        List<Employee> employees = new ArrayList<>();
	        Employee employee = new Employee("fthis.employee", "lthis.employee", "thisemployee@gmail.com", "40000",
	          "HR");

	 

	        employees.add(employee);
	        when(employeeService.listAll()).thenReturn(employees);

	 

	        RequestBuilder request = MockMvcRequestBuilders.get("/api/employees/export?type=pdf").accept(MediaType.APPLICATION_JSON);

	 

	        mockMvc.perform(request).andExpect(status().isOk());
	    }

		/* Test for downloading the Excel sheet */
	    @Test
	    public void downloadExcel() throws Exception {
	        List<Employee> employees = new ArrayList<>();
	        Employee employee = new Employee("fthis.employee", "lthis.employee", "thisemployee@gmail.com", "40000",
	  	          "HR");

	 

	        employees.add(employee);
	        when(employeeService.listAll()).thenReturn(employees);

	 

	        RequestBuilder request = MockMvcRequestBuilders.get("/api/employees/export?type=xlsx").accept(MediaType.APPLICATION_JSON);

	 

	        mockMvc.perform(request).andExpect(status().isOk());
	    }

		/* Test for downloading the CSV */
	    @Test
	    public void downloadcsv() throws Exception {
	        List<Employee> employees = new ArrayList<>();
	        Employee employee = new Employee("fthis.employee", "lthis.employee", "thisemployee@gmail.com", "40000",
	  	          "HR");

	 

	        employees.add(employee);
	        when(employeeService.listAll()).thenReturn(employees);

	 

	        RequestBuilder request = MockMvcRequestBuilders.get("/api/employees/export?type=csv").accept(MediaType.APPLICATION_JSON);

	 

	        mockMvc.perform(request).andExpect(status().isOk());
	    }

	 
}
