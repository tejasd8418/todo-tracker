import { Component, EventEmitter, Inject, Input, OnChanges, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Category } from 'src/app/model/category';
import { Todo } from 'src/app/model/todo';
import { ArchivesServiceService } from 'src/app/service/archivesService/archives-service.service';
import { TodoServiceService } from 'src/app/service/todoService/todo-service.service';
import { HomeComponent } from '../home.component';

@Component({
  selector: 'app-create-todo',
  templateUrl: './create-todo.component.html',
  styleUrls: ['./create-todo.component.css']
})
export class CreateTodoComponent implements OnInit, OnChanges {

  constructor(private fb: FormBuilder, @Inject(MAT_DIALOG_DATA) public data: any,@Inject(MAT_DIALOG_DATA) public data1: Category, private todoService: TodoServiceService,  public dialogRef: MatDialogRef<CreateTodoComponent> , private archivesService: ArchivesServiceService) { }

  ngOnInit(): void {
    console.log(this.data.todo);
    if (this.data.todo != null) {
      this.createTodoForm?.setValue(this.data.todo);
    }
  }

  ngOnChanges() {

  }

  @Input()
  selectedTodo1!: Todo;

  @Input()
  selectedCategory!: Category;

  @Output()
  updateTodo = new EventEmitter();

  @Input()
  changeMade!: string;

  todayDate: Date = new Date();


  createTodoForm = this.fb.group({
    todoId: null,
    image: null,
    guest: null,
    guests: null,
    todoTitle: [null, Validators.required],
    todoContent: [null, Validators.required],
    dueDate: [null, Validators.required],
    highPriority: [false],
    completed: [false],
    categoryId: null,
    emailId: null,
    dateAdded: null
  });

  onSubmit() {
    console.log(this.createTodoForm.value.todoId);
    // console.log(this.selectedCategory.categoryId);
    // console.log(this.data1.categoryId);

    if (this.createTodoForm.value.todoId == null) {
      const todo1: Todo = this.createTodoForm.value;
      todo1.emailId = "tejas@gmail.com";
      todo1.categoryId = this.data1.categoryId;


      this.todoService.saveTodo(todo1, todo1.emailId, todo1.categoryId).subscribe(data => {
        console.log(data);
        this.onClose();
      })
    }
    else if(this.createTodoForm.value.todoId != null){
      console.log(this.data.todo);
      
      this.todoService.updateTodo(this.createTodoForm.value, "tejas@gmail.com", this.data.todo.categoryId).subscribe(data =>{
        console.log(data);
        this.onClose();
      })
    }


  }

  onClose() {
    this.createTodoForm.reset();
    // this.categoryListComponent.ngOnInit(); 
    this.dialogRef.close();
    // this.router.navigateByUrl("");
  }


}
