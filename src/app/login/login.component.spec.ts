import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { TokenStorageService } from '../services/token-storage.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  /* mocking the window object to reload page */
  const myWindow = {
    location: {
      reload() { return 'something'; }
    }
  };

  beforeEach(() => {
    const authServiceStub = () => ({
      login: (username: any, password: any) => ({ subscribe: (f: (arg0: {}) => any) => f({}) }),
      saveUsername: (username: any) => ({})
    });
    const tokenStorageServiceStub = () => ({
      getToken: () => ({}),
      getUser: () => ({ roles: {} }),
      saveToken: (accessToken: any) => ({}),
      saveUser: (data: any) => ({})
    });
    const routerStub = () => ({ navigateByUrl: (string: any) => ({}) });
    TestBed.configureTestingModule({
      imports: [FormsModule],
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [LoginComponent],
      providers: [
        { provide: AuthService, useFactory: authServiceStub },
        { provide: TokenStorageService, useFactory: tokenStorageServiceStub },
        { provide: Router, useFactory: routerStub }
      ]
    });
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    component.compWindow =  myWindow;
    fixture.detectChanges();
  });

  /* should load the instance */
  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  /* should have isLoggedIn has default value */
  it(`isLoggedIn has default value`, () => {
    expect(component.isLoggedIn).toEqual(true);
  });

  /* should have isLoginFailed has default value */
  it(`isLoginFailed has default value`, () => {
    expect(component.isLoginFailed).toEqual(false);
  });

  /* should have roles as default value */
  it(`roles has default value`, () => {
    expect(component.roles).toBeUndefined;
  });

  /* should have invalidAttemptCounter variable has default value */
  it(`invalidAttemptCounter has default value`, () => {
    expect(component.invalidAttemptCounter).toEqual(3);
  });

  /* should make expected calls */
  describe('ngOnInit', () => {
    it('makes expected calls', () => {
      const tokenStorageServiceStub: TokenStorageService = fixture.debugElement.injector.get(
        TokenStorageService
      );
      spyOn(tokenStorageServiceStub, 'getToken').and.callThrough();
      spyOn(tokenStorageServiceStub, 'getUser').and.callThrough();
      component.ngOnInit();
      expect(tokenStorageServiceStub.getToken).toHaveBeenCalled();
      expect(tokenStorageServiceStub.getUser).toHaveBeenCalled();
    });
  });

  /* should render onSubmit function */
  describe('onSubmit', () => {
    it('makes expected calls', () => {
      const authServiceStub: AuthService = fixture.debugElement.injector.get(
        AuthService
      );
      const tokenStorageServiceStub: TokenStorageService = fixture.debugElement.injector.get(
        TokenStorageService
      );
      const routerStub: Router = fixture.debugElement.injector.get(Router);
      spyOn(component, 'reloadPage').and.callThrough();
      spyOn(authServiceStub, 'login').and.callThrough();
      spyOn(authServiceStub, 'saveUsername').and.callThrough();
      spyOn(tokenStorageServiceStub, 'saveToken').and.callThrough();
      spyOn(tokenStorageServiceStub, 'saveUser').and.callThrough();
      spyOn(tokenStorageServiceStub, 'getUser').and.callThrough();
      spyOn(routerStub, 'navigateByUrl').and.callThrough();
      component.onSubmit();
      expect(component.reloadPage).toHaveBeenCalled();
      expect(authServiceStub.login).toHaveBeenCalled();
      expect(authServiceStub.saveUsername).toHaveBeenCalled();
      expect(tokenStorageServiceStub.saveToken).toHaveBeenCalled();
      expect(tokenStorageServiceStub.saveUser).toHaveBeenCalled();
      expect(tokenStorageServiceStub.getUser).toHaveBeenCalled();
      expect(routerStub.navigateByUrl).toHaveBeenCalled();
    });
  });
  
  /* should reload the page */
  describe('reloadPage', () => {
    it('makes expected calls', () => {
      const routerStub: Router = fixture.debugElement.injector.get(Router);
      spyOn(routerStub, 'navigateByUrl').and.callThrough();
      component.reloadPage();
      expect(routerStub.navigateByUrl).toHaveBeenCalled();
    });
  });
});
