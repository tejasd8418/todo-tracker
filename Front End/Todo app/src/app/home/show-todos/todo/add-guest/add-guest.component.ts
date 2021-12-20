import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Category } from 'src/app/model/category';
import { Guest } from 'src/app/model/guest';

import { Todo } from 'src/app/model/todo';
import { GuestServiceService } from 'src/app/service/guestService/guest-service.service';
import { TodoServiceService } from 'src/app/service/todoService/todo-service.service';

@Component({
  selector: 'app-add-guest',
  templateUrl: './add-guest.component.html',
  styleUrls: ['./add-guest.component.css']
})
export class AddGuestComponent implements OnInit {

  constructor(private fb: FormBuilder, private todoService: TodoServiceService, private guestService: GuestServiceService, @Inject(MAT_DIALOG_DATA) public data: Todo, public dialogRef: MatDialogRef<AddGuestComponent>) { }

  ngOnInit(): void {
  }



  addGuestForm = this.fb.group({
    guestId: null,
    guestEmailId: [null, [Validators.required, Validators.email]]
  });


  onSubmit() {
    const guest: Guest = new Guest();
    guest.guestEmailId = this.addGuestForm.value.guestEmailId;
    console.log(this.data);
    console.log(guest);


    this.todoService.addGuest(guest, sessionStorage.getItem('emailId'), this.data.categoryId, this.data.todoId).subscribe(data => {
      console.log(data);
    });

    const category1: Category = new Category("Assigned Task by " + sessionStorage.getItem('emailId'));
    category1.emailId = guest.guestEmailId;

    this.guestService.getCategoryId(guest.guestEmailId, category1.categoryName).subscribe(foundId => {
      console.log(foundId);
      if (foundId != 0) {
        this.guestService.saveTodo(this.data, guest.guestEmailId, foundId).subscribe(data => {
          console.log(data);
        })
      }
      if (foundId == 0) {
        this.guestService.saveCategory(guest.guestEmailId, category1).subscribe(newCategory => {
          console.log(newCategory);
          this.guestService.getCategoryId(guest.guestEmailId, category1.categoryName).subscribe(foundId => {
            if (foundId != 0) {
              this.guestService.saveTodo(this.data, guest.guestEmailId, foundId).subscribe(data => {
                console.log(data);
                this.onClose();
              })
            }
          })
        });
      }
    },
    error => {
      console.log(error);
      
    });
    
  }

  onClose() {
    this.addGuestForm.reset();
    this.dialogRef.close();
  }
}
