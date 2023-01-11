import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './register.component';

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;

  beforeEach(() => {
    const authServiceStub = () => ({
      register: (username: any, email: any, password: any) => ({ subscribe: (f: (arg0: {}) => any) => f({}) })
    });
    TestBed.configureTestingModule({
      imports: [FormsModule],
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [RegisterComponent],
      providers: [{ provide: AuthService, useFactory: authServiceStub }]
    });
    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
  });

  /* should load the instance */
  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  /* should have isSuccessful has default value */
  it(`isSuccessful has default value`, () => {
    expect(component.isSuccessful).toEqual(false);
  });

  /* should have isSignUpFailed has default value */
  it(`isSignUpFailed has default value`, () => {
    expect(component.isSignUpFailed).toEqual(false);
  });

  /* should call ngOninit */
  it('should call ngOnint',()=>{
    component.ngOnInit();
  });

  /* should render onSubmit function */
  describe('onSubmit', () => {
    it('makes expected calls', () => {
      const authServiceStub: AuthService = fixture.debugElement.injector.get(
        AuthService
      );
      spyOn(authServiceStub, 'register').and.callThrough();
      component.onSubmit();
      expect(authServiceStub.register).toHaveBeenCalled();
    });
  });
});
