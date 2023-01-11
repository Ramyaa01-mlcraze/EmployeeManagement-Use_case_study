import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import {HttpClientModule} from '@angular/common/http';
import { CreateEmployeeComponent } from './create-employee.component';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { EmployeeListComponent } from '../employee-list/employee-list.component';
import { InjectionToken } from '@angular/core';

describe('CreateEmployeeComponent', () => {
  let component: CreateEmployeeComponent;
  let fixture: ComponentFixture<CreateEmployeeComponent>;
  const myWindow = {
    location: {
      reload() { return 'something'; }
    }
  };
  let obj:any;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateEmployeeComponent ],
      imports: [HttpClientTestingModule, FormsModule,HttpClientModule,RouterTestingModule.withRoutes([
        {path:"employees",component:EmployeeListComponent}
      ])],
      
    })
    .compileComponents();

    
    fixture = TestBed.createComponent(CreateEmployeeComponent);
    component = fixture.componentInstance;
    component.compWindow =  myWindow;
    fixture.detectChanges();
  });

  /* Should create the component */
  it('should create', () => {
    expect(component).toBeTruthy();
  });

  /* Should go to the List */
  it('should go to the list',()=>{
    expect(component.gotoList()).toBeUndefined();
  });

  /* Should reload the page */
  it('should reload page',()=>{
    component.reloadPage();
  });

  /* Should create a new employee */
  it('should create a new employee',()=>{
    component.newEmployee();
  });

  /* Should save the form */
  it('should save the form',()=>{
    component.save();
  });

  /* The onSubmit function to be called */
  it('should submit',()=>{
    component.onSubmit();
  });
});