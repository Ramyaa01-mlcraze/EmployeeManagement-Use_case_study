import { RouterTestingModule } from '@angular/router/testing';
import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { AuthGuard } from './auth.guard';
import { LoginComponent } from '../login/login.component';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

describe('AuthGuard', () => {
  let guard: AuthGuard;
  let service: AuthService;
  let route:Router;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientModule, HttpClientTestingModule, RouterTestingModule.withRoutes(
        [{path: 'login', component: LoginComponent}]
      )],
      declarations:[LoginComponent]
    });
    guard = TestBed.inject(AuthGuard);
    service = TestBed.inject(AuthService);
    route = TestBed.inject(Router);
  });

  /* Guard should be created */
  it('should be created', () => {
    expect(guard).toBeTruthy();
  });

  /* Should get activated */
  describe('can Activate',()=>{
    it('should get activated',
    ()=>{
      guard.canActivate();
      
      const loggedIn = service.isLoggedIn();
      if(loggedIn){
        return true;
      }
      
      return false;
    });
  });
});
