import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';

import { AuthGuard } from './services/auth.guard';


const routes: Routes = [
  /* Default route */
  {
    path:'',
    component: HomeComponent
  },

  /* Route for Home Component */
  {
    path:'home',
    component: HomeComponent
  },

  /* Route for Dashboard Component */  
  {
    path:'dashboard',
    component:DashboardComponent,
    canActivate:[AuthGuard]
  },

  /* Route for Login Component */
  {
    path:'login',
    component:LoginComponent
  },

  /* Route for Register Component */
  {
    path:'register',
    component:RegisterComponent
  },

  /* Route for Profile Component */
  {
    path:'profile',
    component:ProfileComponent,
    canActivate:[AuthGuard]
  },
 
  /* Route for Employee List Component */
  {
    path:'employees',
    component:EmployeeListComponent,
    canActivate:[AuthGuard]
  },

  /* Route for Create Employee Component */
  {
    path:'add',
    component:CreateEmployeeComponent,
    canActivate:[AuthGuard]
  },

  /* Route for Employee Details Component */
  {
    path:'details/:id',
    component:EmployeeDetailsComponent,
    canActivate:[AuthGuard]
  },
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
