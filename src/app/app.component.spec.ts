import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Token } from '@angular/compiler';
import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { NgxPaginationModule } from 'ngx-pagination';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';
import { AuthService } from './services/auth.service';
import { TokenStorageService } from './services/token-storage.service';

const myWindow = {
  location: {
    reload() { return 'something'; }
  }
};

describe('AppComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([
          {path:'dashboard',component:DashboardComponent},
          {path:'home',component:HomeComponent}
        ]),
        HttpClientModule,
        HttpClientTestingModule,
        
      ],
      declarations: [
        AppComponent
      ],
      providers:[AuthService,TokenStorageService],
    }).compileComponents();
    
    
  });
  beforeEach(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const component = fixture.componentInstance;
    fixture.detectChanges();
  });

  /* Should create the app */
  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
    
  });

  /* Should have as title 'Employee Management System */
  it(`should have as title 'Employee Management System'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('Employee Management System');
  });

  /* Should verify the test */
  it(`should verify the test`,()=>{
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    app.ngOnInit();
    
    
  });

  /* Should logout */
  it('should logout',()=>{
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    app.compWindow = myWindow;
    fixture.detectChanges();
    app.logout();
  });


  
});
