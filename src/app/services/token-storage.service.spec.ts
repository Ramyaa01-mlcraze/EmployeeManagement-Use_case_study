import { TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { HomeComponent } from '../home/home.component';

import { TokenStorageService } from './token-storage.service';

describe('TokenStorageService', () => {
  let service: TokenStorageService;
  let router: Router;
  

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers:[TokenStorageService],
      imports:[RouterTestingModule.withRoutes(
        [{path: 'home', component: HomeComponent}]
      )],
      declarations:[HomeComponent]
      
    });
    service = TestBed.inject(TokenStorageService);
    router=TestBed.inject(Router)
    
  });

  /* Should be created */
  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  /* Should store the token in SessionStorage */
  describe('saveToken', () => {
    it('should store the token in SessionStorage',
      () => {
        service.saveToken('sometoken');
        window.sessionStorage.removeItem('sometoken');
        window.sessionStorage.setItem('auth-token','sometoken');
    });
  });

  /* Should return stored token from SessionStorage */
  describe('getToken', () => {
    it('should return stored token from SessionStorage',
      () => {
       service.getToken();
       window.sessionStorage.getItem('auth-token');
    });
  });

  /* Should save user */
  describe('saveUser',()=>{
    it('should save user from',
    ()=>{
     service.saveUser("user");
     window.sessionStorage.removeItem('auth-user');
     window.sessionStorage.setItem('auth-user',JSON.stringify("user"))
    });
  });

  /* Should get user */
  describe('getUser',()=>{
    it('should get user',
    ()=>{
      service.getUser();
      const user = window.sessionStorage.getItem('USER_KEY');
      if(user){
        return JSON.parse(user);
      }
      return {};
    });
  });

  /* Should sign out */ 
  describe("signOut",()=>{
    it('should sign out',
    ()=>{
      
      service.signOut();
      window.sessionStorage.clear();
     
    })
  })
});
