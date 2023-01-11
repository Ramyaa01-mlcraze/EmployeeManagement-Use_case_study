import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private auth: AuthService, private router: Router){

  }

  /* Should work when the user is not Logged in */
  public canActivate():boolean{
    const loggedIn = this.auth.isLoggedIn();
    if(loggedIn){
      return true;
    }
    this.router.navigate(['login']);
    return false;
  }
}
  

