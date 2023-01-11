import { Injectable } from '@angular/core';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenStorageService } from '../services/token-storage.service';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private token: TokenStorageService) {}
  /* Intercept all the request with Token as Bearer */
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    const token = this.token.getToken();
    
    /* To check if the token is null or not */
    if(token != null){
      authReq = req.clone({headers:req.headers.set(TOKEN_HEADER_KEY, 'Bearer '+ token)});
    }
    return next.handle(authReq);
  }
}

export const authInterceptorProviders =[
  {provide : HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi:true}
];
