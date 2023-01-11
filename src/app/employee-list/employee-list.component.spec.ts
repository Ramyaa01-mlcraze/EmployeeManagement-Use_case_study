import { ComponentFixture, TestBed , fakeAsync, tick} from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { EmployeeListComponent } from './employee-list.component';

import {HttpClientTestingModule} from '@angular/common/http/testing'

import { FormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { RouterTestingModule } from '@angular/router/testing';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { EmployeeService } from '../employee.service';
import { TokenStorageService } from '../services/token-storage.service';
import { EmployeeDetailsComponent } from '../employee-details/employee-details.component';

import { Router } from '@angular/router';

describe('EmployeeListComponent', () => {
  let component: EmployeeListComponent;
  let fixture: ComponentFixture<EmployeeListComponent>;
  const routerSpy = jasmine.createSpyObj('Router', ['navigateByUrl']);

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeeListComponent ],
      imports: [HttpClientTestingModule, FormsModule,NgxPaginationModule,RouterTestingModule.withRoutes([
        {path:'details',component:EmployeeDetailsComponent}
      ]),Ng2SearchPipeModule],
      providers:[EmployeeService,TokenStorageService,{provide:Router, useValue: routerSpy}]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  /* Should create the component */
  it('should create', () => {
    expect(component).toBeTruthy();
  });

  /* Should call inline update */
  it('should call inLine update',()=>{
    component.inLineUpdate(21501);
  });

  /* Should call change value method */
  it('should call change value',()=>{
    component.changeValue('change');
  });

  /* Should call field method */
  it('should call field',()=>{
    component.fieldsChange('field');
  });

  /* Should call change page method */
  it('should change Page',()=>{
    component.pageChange("changed");
  });

  /* Should call selectID method */
  it('should select the ID',()=>{
    component.selectID(21501,'selected');
  });

  /* Should delete the selected */
  it('should select the deleted',()=>{
    component.deleteSelected();
  });

  /* Should delete the employee by id */
  it('should delete employee',()=>{
    component.deleteEmployee(21501);
  });

  /* Should call employeeDetails method */
  it('should tell ROUTER to navigate when button clicked', () => {
   component.employeeDetails(21501);
  });

  /* Should call TableDataChange method */
  it('should change the table data',()=>{
    component.onTableDataChange("changed");
  });

  /* Should call TableSizeChange method */
  it('should change the table size',()=>{
    component.onTableSizeChange("changed");
  });

  /* Should download the excel */
  it('should download the excel',()=>{
    let service = TestBed.inject(EmployeeService);
    spyOn(service,'exportExcel').and.callThrough();
    component.downloadExcel();
    expect(service.exportExcel).toHaveBeenCalled();
  });
  
  /* Should refresh data */
  it('should refresh data',()=>{
    component.refreshData();
  });

  /* Should do group delete action */
  it('should group delete',()=>{
    component.onClickGroupDelete();
  });

  /* Should call onClick method */
  it('should on click',()=>{
    component.onClick('button');
  });

  /* Should call download PDF method */
  it('should download pdf',()=>{
    component.downloadPdf();
  });

  /* Should call download CSV method */
  it('should download csv',()=>{
    component.downloadCsv();
  });
});
