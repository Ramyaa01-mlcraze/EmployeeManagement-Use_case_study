import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { TokenStorageService } from '../services/token-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  compWindow:any;
  form: any={
    username:null,
    password:null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage='';
  roles: string[]=[];
  username?:any;
  invalidAttemptCounter=3;

  constructor(private authService: AuthService, private tokenStorage:TokenStorageService, private route:Router) { 
    this.compWindow=window;
  }

  ngOnInit(): void {
    if(this.tokenStorage.getToken()){
      this.isLoggedIn=true;
      this.username=this.username;
      this.roles=this.tokenStorage.getUser().roles;
    }
  }

  /* should render onSubmit function */
  onSubmit(): void{
    const {username, password} = this.form;

    this.authService.login(username,password).subscribe(
      data =>{
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);
        this.isLoginFailed=false;
        this.isLoggedIn=true;
        this.roles=this.tokenStorage.getUser().roles;
        this.reloadPage();
        this.username = username;
        this.authService.saveUsername(username);
        
        
      },

      err =>{
        this.isLoginFailed=true;
        this.invalidAttemptCounter--;
        alert("Attention!! Inavlid Login Attempt. You are left with " + this.invalidAttemptCounter + " attempts");
        if(this.invalidAttemptCounter===0){
          alert("Account is Locked for 30 seconds");
          (document.getElementById("username") as any).disabled=true;
          (document.getElementById("password") as any).disabled=true;
          (document.getElementById("submit-btn") as any).disabled=true;
          setTimeout(()=> {
            this.route.navigateByUrl('/home');
          },
          30000); 

        }
        this.errorMessage = err.error.message;

      }
        
    );
  }
  
  /* should render reloadPage function */
  reloadPage(): void{
    this.compWindow.location.reload();
    this.route.navigateByUrl('/dashboard');
  }
  

}
