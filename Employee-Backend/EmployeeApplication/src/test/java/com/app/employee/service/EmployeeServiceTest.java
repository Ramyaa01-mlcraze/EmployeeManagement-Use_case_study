package com.app.employee.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.employee.model.EmployeeSequence;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeServiceTest {


	    @InjectMocks
	    private EmployeeService employeeService;

	    @Mock
	    private MongoOperations mongoOperations;

	    @Mock
	    private Query query;

	    @Mock 
	    private Update update;

	    @Mock
	    private EmployeeSequence employeeSequence;
	    
	    

		/* Testing the employeeService class */
	    @Test
	    public void generateEmployeeSequence() {

	        long ans = this.employeeService.generateEmployeeSequence("employee_sequences");

	        assertThat(ans).isNotNull();
	    }
	    
	    @Test
	    public void listAll() {
	    	this.employeeService.listAll();
	    }
}

