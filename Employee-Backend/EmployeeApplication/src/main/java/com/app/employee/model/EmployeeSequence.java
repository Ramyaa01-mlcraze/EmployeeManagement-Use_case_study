package com.app.employee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*Creating a collection to store auto-incremented sequences or ID's*/
@Document(collection = "employee_sequences")
public class EmployeeSequence {
	@Id
	private String id;
	
	private long seq;
	
	public EmployeeSequence() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	
	

}
