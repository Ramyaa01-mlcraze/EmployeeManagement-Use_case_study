import { HttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { EmployeeService } from '../employee.service';
import { TokenStorageService } from '../services/token-storage.service';

import { AuthInterceptor } from './auth.interceptor';

describe('AuthInterceptor', () => {
  let interceptor: AuthInterceptor;
  let httpTestingController: HttpTestingController;
  let mockTokenService : TokenStorageService;
  let service : EmployeeService;
  
  beforeEach(async() => {
    TestBed.configureTestingModule({
    imports : [HttpClientTestingModule],
    providers: [
      EmployeeService,TokenStorageService,{provide : HTTP_INTERCEPTORS, useClass : AuthInterceptor, multi : true},
    ]
  });
  mockTokenService = TestBed.inject(TokenStorageService);
  httpTestingController = TestBed.inject(HttpTestingController);
  service = TestBed.inject(EmployeeService);

  });


  afterEach(()=>{
    httpTestingController.verify();
  });

  /* should mock the interceptor */
  describe('can Mock',()=>{
  it('should mock the interceptor',()=>{
    service.getEmployeesList().subscribe(res=>{
      expect(res).toBeTruthy;
    });
    const httpReq = httpTestingController.expectOne('http://localhost:8080/api/employees');
    if(mockTokenService!=null){
      expect(httpReq.request.headers.has('Authorization')).toBeFalse;
    }
    
  });
});
});
