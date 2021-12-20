import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserServiceService } from 'src/app/service/userService/user-service.service';

@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.component.html',
  styleUrls: ['./about-us.component.css']
})
export class AboutUsComponent implements OnInit {

  constructor(private userService: UserServiceService, private router: Router) { }

  ngOnInit(): void {
  }

  
  logout(){
    this.userService.logOut();
    this.router.navigate(['/accounts/login']);
  }

}
