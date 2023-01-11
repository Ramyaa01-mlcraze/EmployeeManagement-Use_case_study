package com.app.employee.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.app.employee.download.EmployeeExcelExporter;
import com.app.employee.download.EmployeePdfExporter;
import com.app.employee.exceptions.EmployeeNotFoundException;
import com.app.employee.model.Employee;
import com.app.employee.repository.EmployeeRepository;
import com.app.employee.service.EmployeeService;
import com.lowagie.text.DocumentException;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders="*")
@RestController
@RequestMapping("/api")

public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	/* Retrieving the list of employees */
	@GetMapping("/employees") 
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	

	/* Retrieving the employees by id */
	@GetMapping("/employees/{id}") 
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long employeeId) throws EmployeeNotFoundException{
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()-> new EmployeeNotFoundException("Employee Not Found"));
		return ResponseEntity.ok().body(employee);
		
	}
	
	/* Creating an employee */
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		employee.setId(employeeService.generateEmployeeSequence(Employee.SEQUENCE_NAME));
		return employeeRepository.save(employee);
	}
	
	/* Updating the employee by id */
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value= "id")Long employeeId,
		@Valid @RequestBody Employee employeeInfo) throws EmployeeNotFoundException{
			Employee employee = employeeRepository.findById(employeeId).orElseThrow(()-> new EmployeeNotFoundException("Employee Not Found"));
			employee.setempEmailID(employeeInfo.getempEmailID());
			employee.setempLastName(employeeInfo.getempLastName());
			employee.setempFirstName(employeeInfo.getempFirstName());
			employee.setempSalary(employeeInfo.getempSalary());
			employee.setempAllocatedJobs(employeeInfo.getempAllocatedJobs());
			final Employee updatedEmployee = employeeRepository.save(employee);
			return ResponseEntity.ok(updatedEmployee);
	}
	
	/* Deleting the employee by id */
	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value="id") Long employeeId) throws EmployeeNotFoundException{
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new EmployeeNotFoundException("Employee Not Found"));
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Employee is deleted successfully",Boolean.TRUE);
		return response;
	}
	
	/* Export the data in PDF,Excel and CSV using Query Parameters */
	@GetMapping("/employees/export")
	public void exportToFile(HttpServletRequest req, HttpServletResponse response)throws DocumentException, IOException{
		String typeFile = req.getParameter("type");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	    String currentDateTime = dateFormatter.format(new Date());
	    String headerKey = "Content-Disposition";
		List<Employee> listEmployees = employeeService.listAll();
		if(typeFile != null && typeFile.equals("xlsx")) {
			 
		     String headerValue = "attachment; filename=employees_" + currentDateTime + ".xlsx";
		     response.setHeader(headerKey, headerValue);
			 EmployeeExcelExporter excelExporter = new EmployeeExcelExporter(listEmployees);
		     excelExporter.export(response);
		 }
		 
		 else if(typeFile != null && typeFile.equals("pdf")) {
			 response.setContentType("application/pdf");
		     
		     String headerValue = "attachment; filename=employees_" + currentDateTime + ".pdf";
		     response.setHeader(headerKey, headerValue);
		     EmployeePdfExporter exporter = new EmployeePdfExporter(listEmployees);
		     exporter.export(response);
		 }
		 
		 else if(typeFile != null && typeFile.equals("csv")){
			 response.setContentType("text/csv");
		     String headerValue = "attachment; filename=employees_" + currentDateTime + ".csv";
		     response.setHeader(headerKey, headerValue);
		 
		     ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		     String[] csvHeader = {"Employee ID", "First Name", "Last Name", "Email ID", "Salary", "Allocated Job"};
		     String[] nameMapping = {"id", "EmpFirstName", "EmpLastName", "EmpEmailID", "EmpSalary", "EmpAllocatedJobs"};
		         
		     csvWriter.writeHeader(csvHeader);
		         
		     for (Employee employee : listEmployees) {
		    	 csvWriter.write(employee, nameMapping);
		        }
		         
		     csvWriter.close();
		         
			 }
		 }
		 }
	
	