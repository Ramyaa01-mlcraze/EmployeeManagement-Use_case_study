import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../services/token-storage.service';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  compWindow:any;
  currentUser: any
  form: any={
    username:null,
    password:null
  };
  isLoggedin=false;
  isLoginFailed=false;
  checkPass!:boolean;
  pass:string="";
  confirm:string="";
  
  constructor(private token : TokenStorageService, private authService: AuthService, private route: Router) {
    this.compWindow=window;
   }

  ngOnInit(): void {
  this.isLoggedin=true;
  this.currentUser=this.token.getUser();
  this.form.username=this.authService.getUsername();
  
  }

  /* Should render onSubmit method */
  onSubmit():void{
  const {username} = this.form;
  
  if(this.pass !== this.confirm){
    this.checkPass=true;
  } else{
    this.checkPass=false;
    this.form.password = this.pass;
    this.authService.newChangePassword(username,this.form).subscribe();
    alert("You will be logged out");
    this.logout();
  }
  }

  /* Should reload the page */
  reloadPage(): void{
    this.compWindow.location.reload();
    this.route.navigateByUrl('/home');
  }

  /* Should call the logOut method */
  logout():void{
    this.token.signOut();
    this.authService.destroyUsername();
    this.compWindow.location.reload();
  }
  
}
