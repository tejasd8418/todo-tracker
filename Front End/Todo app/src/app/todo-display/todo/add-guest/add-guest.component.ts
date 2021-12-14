import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Category } from 'src/app/model/category';
import { Guest } from 'src/app/model/guest';
import { Status } from 'src/app/model/status';
import { Todo } from 'src/app/model/todo';
import { GuestServiceService } from 'src/app/service/guestService/guest-service.service';
import { TodoServiceService } from 'src/app/service/todoService/todo-service.service';

@Component({
  selector: 'app-add-guest',
  templateUrl: './add-guest.component.html',
  styleUrls: ['./add-guest.component.css']
})
export class AddGuestComponent implements OnInit {

  constructor(private fb: FormBuilder, private todoService: TodoServiceService, private guestService: GuestServiceService, @Inject(MAT_DIALOG_DATA) public data: Todo) { }

  ngOnInit(): void {
  }



  addGuestForm = this.fb.group({
    guestId: null,
    guestEmailId: [null, [Validators.required]]
  });

  
  onSubmit() {
    const guest: Guest = new Guest();
    guest.guestEmailId = this.addGuestForm.value.guestEmailId;
    // this.data.guests.push(this.addGuestForm.value.guestEmailId);
    console.log(this.data);
    console.log(guest);


    this.todoService.addGuest(guest, this.data.emailId, this.data.categoryId, this.data.statusId, this.data.todoId).subscribe(data => {
      console.log(data);
    });

    const status: Status = new Status();
    const category1: Category = new Category("Assigned Task");
    category1.categoryId = 1001;
    category1.emailId = guest.guestEmailId;
    category1.statuses = null;

  this.guestService.saveCategory(guest.guestEmailId, category1).subscribe(data => {
    console.log(data);
  });


  this.guestService.saveTodo(this.data, guest.guestEmailId, category1.categoryId, 1).subscribe(data=>{
    console.log(data);
  })


  }




}
