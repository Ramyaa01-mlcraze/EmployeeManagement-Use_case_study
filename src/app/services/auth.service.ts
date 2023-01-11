import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenStorageService } from '../services/token-storage.service';
import { environment } from 'src/environments/environment';


const httpOptions = {
  headers: new HttpHeaders({'Content-Type' : 'application/json'})
};


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient , private tokenStorage:TokenStorageService) { }
  
  /* Should login */
  login(username : string, password:string): Observable<any>{
    return this.http.post(environment.loginUrl,{
      username,
      password
    }, httpOptions);
  }

   /* Should call the Register service */
  register(username:string, email:string, password:string): Observable<any>{
    return this.http.post(environment.registerUrl, {
      username,
      email,
      password
    }, httpOptions);
  }

  /* Should check whenther the user is Logged In */
  isLoggedIn(){
    return !!this.tokenStorage.getToken();
  }

  /* Should call change password service */
  newChangePassword(username:string, user:{ "username" : string; "password":string}){
    let url = environment.newChangePasswordUrl;

    return this.http.put(`${url}/${username}`, user,httpOptions);
  }

  /* Should save the current username */
  saveUsername(currentUsername : string){
    localStorage.setItem("currentUsername", currentUsername);
  }

  /* Should destory username */
  destroyUsername(){
    localStorage.removeItem("currentUsername");
  }

  /* Should retrieve the current username */
  getUsername(){
    return localStorage.getItem("currentUsername");
  }

  /* Should call the failed attempts service */
  newFailedAttempt(username:string, user:{ "username" : string; "password":string}){
    let url = environment.newFailedAttemptUrl;

    return this.http.put(`${url}/${username}`, user,httpOptions);
  }
}


