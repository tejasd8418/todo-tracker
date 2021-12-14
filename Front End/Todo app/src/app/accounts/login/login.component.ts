import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/class/user';
import { TodoServiceService } from 'src/app/service/todoService/todo-service.service';
import { UserServiceService } from 'src/app/service/userService/user-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm = this.fb.group({
    emailId: [null, Validators.required],
    password: [null, Validators.compose([
      Validators.required, Validators.minLength(2), Validators.maxLength(14)])],
  });
  errorMessage!: string;



  constructor(private fb: FormBuilder,  private registerAccount: UserServiceService, private todoService: TodoServiceService, private router: Router, private route: ActivatedRoute) {}

  onSubmit(): void {
    let newAccount: User = new User();
    
    newAccount.emailId = this.loginForm.value.emailId;
    newAccount.password = this.loginForm.value.password;

    this.registerAccount.loginUser(newAccount).subscribe(data => {
      console.log(data);
      this.router.navigate([""]);
    },

      error => {
        console.log(error)
        this.errorMessage = '';
        if (error.status === 400)
          this.errorMessage = `Failed to fetch data, try again later !!`;
        else if (error.status === 500)
          this.errorMessage = `Service Unavailable !!! Inconvenience Regretted`;
        else
          this.errorMessage = 'Site Unavailable, Try again later !!';
        console.log(error.status);
      });
  }

  register(){
    this.router.navigate(["accounts/register"] );
  }
}
