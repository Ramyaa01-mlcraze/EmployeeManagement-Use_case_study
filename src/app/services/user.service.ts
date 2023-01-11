import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
  
  /* Should retrieve the Public Content */
  getPublicContent(): Observable<any>{
    return this.http.get(environment.PublicUrl, {responseType:'text'});
  }

  /* Should retrieve the User Content */
  getUserBoard(): Observable<any>{
    return this.http.get(environment.UserUrl, {responseType:'text'});
  }

  /* Should retrieve the Admin Content */
  getAdminBoard(): Observable<any>{
    return this.http.get(environment.AdminUrl, {responseType:'text'});
  }
}
