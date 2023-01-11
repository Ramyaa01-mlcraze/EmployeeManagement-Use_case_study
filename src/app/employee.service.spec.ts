import { TestBed } from '@angular/core/testing';

import { EmployeeService } from './employee.service';

import {HttpClientTestingModule,HttpTestingController} from '@angular/common/http/testing'

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Employee } from './employee';

describe('EmployeeService', () => {
  let service: EmployeeService;
  let httpTestingController:HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule,HttpClientModule,FormsModule],
      providers: [EmployeeService]
    });
    service = TestBed.inject(EmployeeService);
    httpTestingController=TestBed.inject(HttpTestingController);
  });

  /* Should be created */
  it('should be created', () => {
    const service: EmployeeService = TestBed.get(EmployeeService);
    expect(service).toBeTruthy();
  });

  /* Should create an Employee on Service method */
  it('should create an Employee on Service method', () => {
    let employee:Employee={"id":215702,"empFirstName":"Ramyaa","empLastName":"Suresh","empEmailID":"ramyaa@gmail.com","empSalary":"50000","empAllocatedJobs":"HR","active":true,"isEdit":false}
    service.createEmployee(employee).subscribe(data => {
      console.log("Created");
      expect(data).toBeTruthy('Should not create an employee.');  
      expect(data).toEqual("Created");    
    }); 
    const req = httpTestingController.expectOne('http://localhost:8080/api/employees');
    expect(req.request.method).toEqual("POST");
    req.flush("Created");
  });

  /* Should retrieve employees on Service method */
  it('should retrieve employees on Service method', () => {
    service.getEmployeesList().subscribe(data => {
      console.log("Retrieved");
      expect(data).toBeTruthy('Should not return the list');  
      expect(data).toEqual("Retrieved");    
    }); 
    const req = httpTestingController.expectOne('http://localhost:8080/api/employees');
    expect(req.request.method).toEqual("GET");
    req.flush("Retrieved");
  });

  /* Should retrieve an Employee with ID on Service method */
  it('should retrieve an Employee with ID on Service method', () => {
    let employee:Employee={"id":215702,"empFirstName":"Ramyaa","empLastName":"Suresh","empEmailID":"ramyaa@gmail.com","empSalary":"50000","empAllocatedJobs":"HR","active":true,"isEdit":false}
    service.getEmployee(215702).subscribe(data => {
      console.log("Retrieved an employee with ID");
      expect(data).toBeTruthy('Should not retrieve.');  
      expect(data).toEqual("Retrieved an employee with ID");    
    }); 
    const req = httpTestingController.expectOne('http://localhost:8080/api/employees/215702');
    expect(req.request.method).toEqual("GET");
    req.flush("Retrieved an employee with ID");
  });

  /* Should update an Employee on Service method */
  it('should update an Employee on Service method', () => {
    let employee:Employee={"id":215702,"empFirstName":"Ramyaa","empLastName":"Suresh","empEmailID":"ramyaa@gmail.com","empSalary":"50000","empAllocatedJobs":"HR","active":true,"isEdit":false}
    service.updateEmployee(215702,employee).subscribe(data => {
      console.log("Updated an employee");
      expect(data).toBeTruthy('Should not update.');  
      expect(data).toEqual("Updated an employee");    
    }); 
    const req = httpTestingController.expectOne('http://localhost:8080/api/employees/215702');
    expect(req.request.method).toEqual("PUT");
    req.flush("Updated an employee");
  });

  /* Should delete an Employee on Service method */
  it('should delete an Employee on Service method', () => {
    let employee:Employee={"id":215702,"empFirstName":"Ramyaa","empLastName":"Suresh","empEmailID":"ramyaa@gmail.com","empSalary":"50000","empAllocatedJobs":"HR","active":true,"isEdit":false}
    service.deleteEmployee(215702).subscribe(data => {
      console.log("Deleted an employee with ID");
      expect(data).toBeTruthy('Should not delete.');  
      expect(data).toEqual("Deleted an employee with ID");    
    }); 
    const req = httpTestingController.expectOne('http://localhost:8080/api/employees/215702');
    expect(req.request.method).toEqual("DELETE");
    req.flush("Deleted an employee with ID");
  });

  /* Should export as Excel file */
  it('should export as Excel file', () => {
    let response = new Blob();
    service.exportExcel().subscribe(data => {
      console.log("Exported as Excel");
      expect(data).toBeTruthy('Should not retrieve.');  
      expect(data).toEqual(response,'Exported as Excel');
    }); 
    const req = httpTestingController.expectOne('http://localhost:8080/api/employees/export?type=xlsx');
    expect(req.request.method).toEqual("GET");
    req.flush(response);
  });

  /* Should export as PDF file */
  it('should export as PDF file', () => {
    let response = new Blob();
    service.exportPdf().subscribe(data => {
      console.log("Exported as PDF");
      expect(data).toBeTruthy('Should not retrieve.');  
      expect(data).toEqual(response,'Exported as PDF');
    }); 
    const req = httpTestingController.expectOne('http://localhost:8080/api/employees/export?type=pdf');
    expect(req.request.method).toEqual("GET");
    req.flush(response);
  });

  /* Should export as CSV file */
  it('should export as CSV file', () => {
    let response = new Blob();
    service.exportCsv().subscribe(data => {
      console.log("Exported as CSV");
      expect(data).toBeTruthy('Should not retrieve.');  
      expect(data).toEqual(response,'Exported as CSV');
    }); 
    const req = httpTestingController.expectOne('http://localhost:8080/api/employees/export?type=csv');
    expect(req.request.method).toEqual("GET");
    req.flush(response);
  });
  
});


