import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserServiceService } from 'src/app/service/userService/user-service.service';

@Component({
  selector: 'app-privacy-policy',
  templateUrl: './privacy-policy.component.html',
  styleUrls: ['./privacy-policy.component.css']
})
export class PrivacyPolicyComponent implements OnInit {

  constructor(private userService: UserServiceService, private router: Router) { }

  ngOnInit(): void {
  }

  logout(){
    this.userService.logOut();
    this.router.navigate(['/accounts/login']);
  }

}
