import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Message } from './class/message';
import { UserServiceService } from './service/userService/user-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Todo App';


  constructor(private userService: UserServiceService, private router: Router){

  }

  currentUser = sessionStorage.getItem('emailId')

 

}







