import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, EventEmitter, Inject, Input, OnChanges, OnInit, Output, Pipe } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { Category } from 'src/app/model/category';
import { Todo } from 'src/app/model/todo';
import { ArchivesServiceService } from 'src/app/service/archivesService/archives-service.service';
import { ImageUploadingService } from 'src/app/service/image/image-uploading.service';
import { TodoServiceService } from 'src/app/service/todoService/todo-service.service';
import { HomeComponent } from '../home.component';

@Component({
  selector: 'app-create-todo',
  templateUrl: './create-todo.component.html',
  styleUrls: ['./create-todo.component.css']
})
export class CreateTodoComponent implements OnInit, OnChanges {

  constructor(private fb: FormBuilder, @Inject(MAT_DIALOG_DATA) public data: any, @Inject(MAT_DIALOG_DATA) public data1: Category, private todoService: TodoServiceService, public dialogRef: MatDialogRef<CreateTodoComponent>, private archivesService: ArchivesServiceService, private imageUploadingService: ImageUploadingService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.fileInfos = this.imageUploadingService.getFiles();

    console.log(this.data.todo);
    if (this.data.todo != null) {
      this.imageUrl1 = this.data.todo.imageUrl;
      this.data.todo.imageUrl = '';
      this.createTodoForm?.setValue(this.data.todo);
    }
  }

  imageUrl1: any;

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

  

  filess = "background.jpg";

  selectedFiles?: FileList;
  currentFile?: File;
  fileInfos?: Observable<any>;
  file1?: Observable<any>;
  fileName2: string = "background.jpg";
  selectFile(event: any) {
    this.selectedFiles = event.target.files;

  }
  upload(): void {
          
    
    
  }


  createTodoForm = this.fb.group({
    todoId: null,
    imageUrl: '',
    guest: null,
    guests: null,
    todoTitle: [null, Validators.required],
    todoContent: [null, Validators.required],
    dueDate: [null, Validators.required],
    highPriority: [false],
    completed: [false],
    categoryId: null,
    archived : null,
    emailId: null,
    dateAdded: null
  });

  onSubmit() {


    let imageValue = this.createTodoForm?.controls?.imageUrl?.value;
    let getActualImageValue = imageValue?.split("\\")[2];
    console.log(getActualImageValue);
    
    
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);
      if (file) {
        this.currentFile = file;

        this.imageUploadingService.upload(this.currentFile).subscribe((event: any) => {
            console.log(event);
          });
      }
      this.selectedFiles = undefined;
    }
    console.log(this.createTodoForm.value.todoId);
  

    if (this.createTodoForm.value.todoId == null) {
      const todo1: Todo = this.createTodoForm.value;
      todo1.categoryId = this.data1.categoryId;
      todo1.imageUrl = "http://localhost:8089/files/" + getActualImageValue;
      
      
      this.todoService.saveTodo(todo1, sessionStorage.getItem('emailId'), todo1.categoryId).subscribe(data => {
        console.log(data);
        this.snackBar.open("Todo Created Successfully", "X");

        this.onClose();
      })
    }
    else if (this.createTodoForm.value.todoId != null) {
      const todo1: Todo = this.createTodoForm.value;
      console.log(this.data.todo);      
      todo1.imageUrl =  this.imageUrl1;
      // console.log(todo1.imageUrl);

      
      this.todoService.updateTodo(todo1, sessionStorage.getItem('emailId'), this.data.todo.categoryId).subscribe(data => {
        console.log(data);
        this.snackBar.open("Todo Updated Successfully", "X");

        this.onClose();
      })
    }


  }

  onClose() {
    this.createTodoForm.reset();
    this.dialogRef.close();
  }


}
