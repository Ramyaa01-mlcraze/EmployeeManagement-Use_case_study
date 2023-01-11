import { Component } from '@angular/core';
import { TokenStorageService } from './services/token-storage.service';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  compWindow:any;
  title = 'Employee Management System';
  private roles: string[] = [];
  isLoggedIn = false;
  username?: string;
  showAddEmployee=false;
 

  constructor(public tokenStorageService: TokenStorageService, private route:Router, private authService : AuthService){
    this.compWindow=window;
  }

  ngOnInit():void{
    this.isLoggedIn=!!this.tokenStorageService.getToken();

    if(this.isLoggedIn){
      const user=this.tokenStorageService.getUser();
      this.route.navigateByUrl('/dashboard');
      this.roles=user.roles;
      this.showAddEmployee=this.roles?.includes('ROLE_ADMIN');
      this.username=user.username;
    }
  }

  /* Should logout */
  logout():void{
    this.tokenStorageService.signOut();
    this.authService.destroyUsername();
    this.compWindow.location.reload();
  }

}
