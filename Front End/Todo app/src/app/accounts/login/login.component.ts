import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
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
  invalidLogin: boolean = false;
  error: any;



  constructor(private fb: FormBuilder,  private userService: UserServiceService, private todoService: TodoServiceService, private router: Router, private route: ActivatedRoute, private snackBar: MatSnackBar) {}

  onSubmit(): void {
    let newAccount: User = new User();
    
    newAccount.emailId = this.loginForm.value.emailId;
    newAccount.password = this.loginForm.value.password;

    this.userService.loginUser(newAccount).subscribe(data => {
      console.log(data);
      this.router.navigate([""]);
      this.invalidLogin = false
      this.snackBar.open("Login Successfully", "X");

    },

      error => {
        this.invalidLogin = true
        this.error = error.message;
        this.snackBar.open("Login Failed", "X");

      });
  }

  register(){
    this.router.navigate(["accounts/register"] );
  }
}
