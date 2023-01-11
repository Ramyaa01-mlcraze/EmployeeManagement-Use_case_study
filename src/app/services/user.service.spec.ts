import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { UserService } from './user.service';

describe('UserService', () => {
  let service: UserService;
  let httpTestingController : HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientModule,HttpClientTestingModule],
      providers:[UserService]
    });
    service = TestBed.inject(UserService);
    httpTestingController=TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  /* Should retrieve the Public Content */
  it('should retrieve the Public Content', () => {
    service.getPublicContent().subscribe(data => {
      console.log("Get Public content");
      expect(data).toBeTruthy('Should not retrieve.');  
      expect(data).toEqual("Get Public Content");    
    }); 
    const req = httpTestingController.expectOne('http://localhost:8080/api/test/all');
    expect(req.request.method).toEqual("GET");
    req.flush("Get Public Content");
  });

  /* Should retrieve the User Content */
  it('should retrieve the User Content', () => {
    service.getUserBoard().subscribe(data => {
      console.log("Get User content");
      expect(data).toBeTruthy('Should not retrieve.');  
      expect(data).toEqual("Get User Content");    
    }); 
    const req = httpTestingController.expectOne('http://localhost:8080/api/test/user');
    expect(req.request.method).toEqual("GET");
    req.flush("Get User Content");
  });

  /* Should retrieve the Admin Content */
  it('should retrieve the Admin Content', () => {
    service.getAdminBoard().subscribe(data => {
      console.log("Get Admin content");
      expect(data).toBeTruthy('Should not retrieve.');  
      expect(data).toEqual("Get Admin Content");    
    }); 
    const req = httpTestingController.expectOne('http://localhost:8080/api/test/admin');
    expect(req.request.method).toEqual("GET");
    req.flush("Get Admin Content");
  });

});
