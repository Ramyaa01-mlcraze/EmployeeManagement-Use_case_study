import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  
  currentUser:any;
  constructor(private userService: UserService, private token : TokenStorageService) { }

  ngOnInit(): void {
    
    /* Current user will store the user who has logged in */
    this.currentUser=this.token.getUser();
  }

}