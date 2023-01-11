import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { TokenStorageService } from '../services/token-storage.service';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ProfileComponent } from './profile.component';

describe('ProfileComponent', () => {
  let component: ProfileComponent;
  let fixture: ComponentFixture<ProfileComponent>;
  const myWindow = {
    location: {
      reload() { return 'something'; }
    }
  };

  beforeEach(() => {
    const tokenStorageServiceStub = () => ({
      getUser: () => ({}),
      signOut: () => ({})
    });
    const authServiceStub = () => ({
      getUsername: () => ({}),
      newChangePassword: (username: any, form: any) => ({ subscribe: (f: (arg0: {}) => any) => f({}) }),
      destroyUsername: () => ({})
    });
    const routerStub = () => ({ navigateByUrl: (string: any) => ({}) });
    TestBed.configureTestingModule({
      imports: [FormsModule],
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [ProfileComponent],
      providers: [
        { provide: TokenStorageService, useFactory: tokenStorageServiceStub },
        { provide: AuthService, useFactory: authServiceStub },
        { provide: Router, useFactory: routerStub }
      ]
    });
    fixture = TestBed.createComponent(ProfileComponent);
    component = fixture.componentInstance;
    component.compWindow =  myWindow;
    fixture.detectChanges();
  });

  /* Should load instance */
  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  /* Should have isLoggedIn hsa default value */
  it(`isLoggedin has default value`, () => {
    expect(component.isLoggedin).toEqual(true);
  });

  /* Should have isLoginFailed hsa default value */
  it(`isLoginFailed has default value`, () => {
    expect(component.isLoginFailed).toEqual(false);
  });

  /* Should make expected calls */
  describe('ngOnInit', () => {
    it('makes expected calls', () => {
      const tokenStorageServiceStub: TokenStorageService = fixture.debugElement.injector.get(
        TokenStorageService
      );
      const authServiceStub: AuthService = fixture.debugElement.injector.get(
        AuthService
      );
      spyOn(tokenStorageServiceStub, 'getUser').and.callThrough();
      spyOn(authServiceStub, 'getUsername').and.callThrough();
      component.ngOnInit();
      expect(tokenStorageServiceStub.getUser).toHaveBeenCalled();
      expect(authServiceStub.getUsername).toHaveBeenCalled();
    });
  });

  /* Should render the onSubmit function */
  describe('onSubmit', () => {
    it('makes expected calls', () => {
      const authServiceStub: AuthService = fixture.debugElement.injector.get(
        AuthService
      );
      spyOn(component, 'logout').and.callThrough();
      spyOn(authServiceStub, 'newChangePassword').and.callThrough();
      component.onSubmit();
      expect(component.logout).toHaveBeenCalled();
      expect(authServiceStub.newChangePassword).toHaveBeenCalled();
    });
  });

  /* Should reload the page */
  describe('reloadPage', () => {
    it('makes expected calls', () => {
      const routerStub: Router = fixture.debugElement.injector.get(Router);
      spyOn(routerStub, 'navigateByUrl').and.callThrough();
      component.reloadPage();
      expect(routerStub.navigateByUrl).toHaveBeenCalled();
    });
  });

  /* Should call the logout Function */
  describe('logout', () => {
    it('makes expected calls', () => {
      const tokenStorageServiceStub: TokenStorageService = fixture.debugElement.injector.get(
        TokenStorageService
      );
      const authServiceStub: AuthService = fixture.debugElement.injector.get(
        AuthService
      );
      spyOn(tokenStorageServiceStub, 'signOut').and.callThrough();
      spyOn(authServiceStub, 'destroyUsername').and.callThrough();
      component.logout();
      expect(tokenStorageServiceStub.signOut).toHaveBeenCalled();
      expect(authServiceStub.destroyUsername).toHaveBeenCalled();
    });
  });
});
