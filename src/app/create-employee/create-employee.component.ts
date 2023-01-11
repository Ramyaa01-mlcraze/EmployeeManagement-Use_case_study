import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {
  compWindow:any;
  employee: Employee = new Employee();
  submitted = false;

  constructor(private employeeService: EmployeeService, private router: Router) {
    this.compWindow=window;
   }

  ngOnInit() {
  }

  /* Method to create new employee */
  newEmployee() : void{
    this.submitted=false;
    this.employee=new Employee();
  }

  /* Method the save the Employee Data in the Database */
  save(){
    this.employeeService.createEmployee(this.employee).subscribe();
      
    this.employee=new Employee();
    this.gotoList();
    
  }

  /* Method for the Add Employee submit Button */
  onSubmit(){
    this.submitted=true;
    this.save();
    this.reloadPage();
  }

  /* Method to navigate back to the list */
  gotoList(){
    this.router.navigate(['/employees']);
  }

  /* Method to reload the page */
  reloadPage(): void{
    this.compWindow.location.reload();
  }


}
