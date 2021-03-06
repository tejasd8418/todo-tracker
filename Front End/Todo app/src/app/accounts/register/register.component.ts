import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/class/user';
import { ArchivesServiceService } from 'src/app/service/archivesService/archives-service.service';

import { TodoServiceService } from 'src/app/service/todoService/todo-service.service';
import { UserServiceService } from 'src/app/service/userService/user-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  registerForm = this.fb.group({
    emailId: [null, [Validators.required, Validators.email]],
    userName: [null, Validators.compose([
      Validators.required, Validators.minLength(5), Validators.maxLength(14)])],
    password: [null, Validators.compose([
      Validators.required, Validators.minLength(5), Validators.maxLength(14)])],
    phoneNo: [null, Validators.compose([
      Validators.required, Validators.minLength(10), Validators.maxLength(10)])],
  });


  errorMessage!: string;

  user!:User;
 



  constructor(private fb: FormBuilder, private registerAccount: UserServiceService, private todoService: TodoServiceService, private archivesService: ArchivesServiceService, private router: Router, private route: ActivatedRoute, private snackBar: MatSnackBar) { }


  onSubmit(): void {


    let newAccount: User = new User();
    
    newAccount.emailId = this.registerForm.value.emailId;
    newAccount.password = this.registerForm.value.password;
    newAccount.phoneNo = this.registerForm.value.phoneNo;
    newAccount.userName = this.registerForm.value.userName;

    this.todoService.registerUser(newAccount).subscribe(data => {
      console.log(data);
      this.archivesService.registerUser(newAccount).subscribe(data=>{
        console.log(data);
          this.router.navigate(["../login"], { relativeTo: this.route });
          this.snackBar.open("Registered Successfully", "X");

        
      })
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
        this.snackBar.open("Registered Failed", "X");

      });


      
  }

  login(){
    this.router.navigate(["../login"] , {relativeTo: this.route});
  }

}
