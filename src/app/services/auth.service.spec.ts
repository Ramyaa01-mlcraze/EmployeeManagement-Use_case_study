import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { AuthService } from './auth.service';

describe('AuthService', () => {
  let service: AuthService;
  let httpTestingController : HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientModule,HttpClientTestingModule],
      providers:[AuthService]
    });
    service = TestBed.inject(AuthService);
    httpTestingController=TestBed.inject(HttpTestingController);
  });
  
  /* Service should be created */
  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  /* Should login */
  it('should Login', () => {
  
    service.login("Ramyaa","Ramyaa_01").subscribe(data=>{
      console.log("Successfull Login");
      expect(data).toBeTruthy('Should not login.');  
      expect(data).toEqual("Successfull Login"); 
    });
    
    const req = httpTestingController.expectOne('http://localhost:8080/api/auth/signin');
    expect(req.request.method).toEqual("POST");
    req.flush("Successfull Login");
  });


  /* Should call the Register service */
  it('should Register', () => {
  
    service.register("Ramyaa","ramyaa@gmail.com","Ramyaa_01").subscribe(data=>{
      console.log("Successfull Register");
      expect(data).toBeTruthy('Should not Register.');  
      expect(data).toEqual("Successfull Register"); 
    });
    
    const req = httpTestingController.expectOne('http://localhost:8080/api/auth/signup');
    expect(req.request.method).toEqual("POST");
    req.flush("Successfull Register");
  });

  /* Should call change password service */
  it('should Change Password', () => {
  
    service.newChangePassword("Ramyaa",{username:"Ramyaa",password:"Ramyaa_01"}).subscribe(data=>{
      console.log("Password Changed successfully");
      expect(data).toBeTruthy('Should not change the password.');  
      expect(data).toEqual("Password Changed successfully"); 
    });
    
    const req = httpTestingController.expectOne('http://localhost:8080/api/auth/change-password/Ramyaa');
    expect(req.request.method).toEqual("PUT");
    req.flush("Password Changed successfully");
  });

  /* Should call the failed attempts service */
  it('should mention failed Attempts', () => {
  
    service.newFailedAttempt("Ramyaa",{username:"Ramyaa",password:"Ramyaa_01"}).subscribe(data=>{
      console.log("Failed Attempts mentioned");
      expect(data).toBeTruthy('Should not mention the faied attempts.');  
      expect(data).toEqual("Failed Attempts mentioned"); 
    });
    
    const req = httpTestingController.expectOne('http://localhost:8080/api/auth/failed-attempt/Ramyaa');
    expect(req.request.method).toEqual("PUT");
    req.flush("Failed Attempts mentioned");
  });

  /* Should save the current username */
  it('should save CurrentUserName',()=>{
    service.saveUsername("Ramyaa");
    localStorage.setItem("Ramyaa","saved");
  });

  /* Should destory username */
  it('should destory username',()=>{
    service.destroyUsername();
    localStorage.removeItem("Ramyaa");
  });

  /* Should check whenther the user is Logged In */
  it('should check whenther the user is Logged In',()=>{
    service.isLoggedIn();
    
  })

});
