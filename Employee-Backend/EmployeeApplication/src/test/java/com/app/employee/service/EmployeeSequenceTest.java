package com.app.employee.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.employee.EmployeeApplication;
import com.app.employee.model.EmployeeSequence;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = EmployeeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeSequenceTest {
	
	@InjectMocks
	EmployeeSequence sequence;
	
	public static final String ID="12";
	public static final long SEQ = 12345;

	/* Testing the EmployeeSequence model class */
	@Test
	public void test() throws Exception {
		this.sequence.getId();
		this.sequence.getSeq();
		this.sequence.setId(ID);
		this.sequence.setSeq(SEQ);
	}
}
