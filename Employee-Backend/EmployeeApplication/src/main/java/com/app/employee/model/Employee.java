package com.app.employee.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/*POJO class for Employee*/
@Document(collection = "employees")
public class Employee {
	
	@Transient 
	public static final String SEQUENCE_NAME = "employee_sequence";
	@Id
	private long id;
	
	@NotBlank 
	@Size(max=100)
	@Indexed(unique=true) 
	private String EmpFirstName;
	private String EmpLastName;
	
	@NotBlank
	@Size(max=100)
	@Indexed(unique=true)
	private String EmpEmailID;
	
	@NotBlank
	@Size(max=100) 
	@Indexed(unique=true)
	private String EmpSalary;
	private String EmpAllocatedJobs;
	
	
	public Employee() {
	}

	public Employee(String EmpFirstName, String EmpLastName, String EmpEmailID, String EmpSalary,
			String EmpAllocatedJobs) {
		this.EmpFirstName = EmpFirstName;
		this.EmpLastName = EmpLastName;
		this.EmpEmailID = EmpEmailID;
		this.EmpSalary = EmpSalary;
		this.EmpAllocatedJobs = EmpAllocatedJobs;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getempFirstName() {
		return EmpFirstName;
	}

	public void setempFirstName(String EmpFirstName) {
		this.EmpFirstName = EmpFirstName;
	}

	public String getempLastName() {
		return EmpLastName;
	}

	public void setempLastName(String EmpLastName) {
		this.EmpLastName = EmpLastName;
	}

	public String getempEmailID() {
		return EmpEmailID;
	}

	public void setempEmailID(String EmpEmailID) {
		this.EmpEmailID = EmpEmailID;
	}

	public String getempSalary() {
		return EmpSalary;
	}

	public void setempSalary(String EmpSalary) {
		this.EmpSalary = EmpSalary;
	}

	public String getempAllocatedJobs() {
		return EmpAllocatedJobs;
	}

	public void setempAllocatedJobs(String EmpAllocatedJobs) {
		this.EmpAllocatedJobs = EmpAllocatedJobs;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", EmpFirstName=" + EmpFirstName + ", EmpLastName=" + EmpLastName
				+ ", EmpEmailID=" + EmpEmailID + ", EmpSalary=" + EmpSalary + ", EmpAllocatedJobs=" + EmpAllocatedJobs
				+ "]";
	}

}
