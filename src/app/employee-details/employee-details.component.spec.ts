import { ComponentFixture, TestBed } from '@angular/core/testing';
import { throwError } from 'rxjs';
import { EmployeeDetailsComponent } from './employee-details.component';

import {HttpClientTestingModule} from '@angular/common/http/testing'

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { EmployeeListComponent } from '../employee-list/employee-list.component';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { of } from 'rxjs';

describe('EmployeeDetailsComponent', () => {
  let component: EmployeeDetailsComponent;
  let fixture: ComponentFixture<EmployeeDetailsComponent>;
  const employeeServiceSpy = jasmine.createSpyObj('EmployeeService', {
    getEmployee: of({/* mock environmentBookingsData here */id:215702})
  });

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeeDetailsComponent ],
      imports: [HttpClientTestingModule, FormsModule,HttpClientModule,RouterTestingModule.withRoutes([{path:'employees',component:EmployeeListComponent}])],
      providers:[{provide : EmployeeService, useValue: employeeServiceSpy}]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeeDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  /* Should create the component */
  it('should create', () => {
    expect(component).toBeTruthy();
  });

  /* Should create the list */
  it('should create list',()=>{
    const fixture = TestBed.createComponent(EmployeeDetailsComponent);
    const app = fixture.componentInstance;
    expect(app.list()).toBeUndefined();
  });

  /* Should retrieve the employee by id */
  it('should get the employee by id',()=>{
    const fixture = TestBed.createComponent(EmployeeDetailsComponent);
    const app = fixture.componentInstance;
    let employee:Employee={"id":215702,"empFirstName":"Ramyaa","empLastName":"Suresh","empEmailID":"ramyaa@gmail.com","empSalary":"50000","empAllocatedJobs":"HR","active":true,"isEdit":false};
    let service = TestBed.inject(EmployeeService);
    app.employee;
    app.id;
    service.getEmployee(215702);
    console.log('No');
    
  });
});
