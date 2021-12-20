import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { CreateTodoComponent } from 'src/app/home/create-todo/create-todo.component';
import { Category } from 'src/app/model/category';
import { Todo } from 'src/app/model/todo';
import { ArchivesServiceService } from 'src/app/service/archivesService/archives-service.service';
import { ImageUploadingService } from 'src/app/service/image/image-uploading.service';
import { TodoServiceService } from 'src/app/service/todoService/todo-service.service';
import { CategoryListComponent } from '../../category-list/category-list.component';
import { HomeComponent } from '../../home.component';

import { AddGuestComponent } from './add-guest/add-guest.component';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit, OnChanges {
  archived!: Todo[];

  constructor(private todoService: TodoServiceService, private dialog: MatDialog, private archivesService: ArchivesServiceService, private categoryListComponent: CategoryListComponent, private homeComponent: HomeComponent, private imageUploadingService: ImageUploadingService, private modalService: NgbModal, private snackBar: MatSnackBar) {
    console.log("todo constructor");
    
    this.todos = [];
  }

  ngOnInit(): void {
    console.log("todo ngOnINit");
    // this.homeComponent.ngOnInit();
    console.log(sessionStorage.getItem('emailId'));
    this.todos = [];
    this.todosToDisplay?.map(element => {
      if (element.completed == false) {
        this.todos.push(element);
      }


    })


    this.file = "todoicon1.png";

    this.fileInfos = this.imageUploadingService.getFiles();

    this.fileInfos.subscribe(data => {
      console.log(data);
      
    })



  }
  file!: string;
  fileData: any;

  ngOnChanges() {
    console.log("todo ngOnChanges");

    this.todos = [];

    this.todosToDisplay?.map(element => {
      if (element.completed == false) {
        this.todos.push(element);
      }
    })

    
  }

  selectedFiles?: FileList;
  currentFile?: File;

  progress = 0;
  message = '';

  fileInfos?: Observable<any>;

  selectFile(event: any) {
    this.selectedFiles = event.target.files;
  }

  @Output()
  selectionChanged = new EventEmitter<Todo>();

  @Input()
  selectedCategory!: Category;

  @Input()
  refresh!: string;

  @Input()
  todosToDisplay!: Todo[];

  @Output()
  changeMade = new EventEmitter();



  todos: Todo[] = [];
  todoView!: Todo;

  displayTodo(longContent: any, todo: Todo){
    this.todoView = todo;
    this.modalService.open(longContent, { scrollable: true });
  }



  deleteTodo(todo: Todo) {
    console.log(todo);
    console.log(this.selectedCategory);
    this.todoService.deleteTodo(sessionStorage.getItem('emailId'), this.selectedCategory.categoryId, todo.todoId).subscribe(data => {
      console.log(data);
      this.categoryListComponent.ngOnInit();
      this.ngOnInit();
      this.homeComponent.updateTodos(this.selectedCategory);
      this.snackBar.open("Todo Deleted Successfully", "X");
    })
  }

  updateTodo(todo: Todo, selectedCategory: Category) {
    console.log(todo);

    const dialog1 = this.dialog.open(CreateTodoComponent, {
      width: '1000px',
      data: { todo, selectedCategory }
    });

    dialog1.afterClosed().subscribe(data => {
      this.snackBar.open("Todo Updated Successfully", "X");

      console.log(data);
      this.homeComponent.updateTodos(this.selectedCategory);
    })

  }

  complete(todo: Todo, selectedCategory: Category) {
    todo.completed = true;
    this.todoService.updateTodo(todo, sessionStorage.getItem('emailId'), todo.categoryId).subscribe(data => {
      console.log(data);
      this.homeComponent.updateTodos(this.selectedCategory);
      this.snackBar.open("Todo Marked as Completed", "X");

    })
  }

  highPriority(todo: Todo, selectedCategory: Category) {
    todo.highPriority = true;
    this.todoService.updateTodo(todo, sessionStorage.getItem('emailId'), todo.categoryId).subscribe(data => {
      console.log(data);
      this.homeComponent.updateTodos(this.selectedCategory);
      this.snackBar.open("Todo Marked as High Priority", "X");

    })
  }

  addGuest(todo: Todo) {
    this.dialog.open(AddGuestComponent, {
      width: '1000px',
      data: todo
    });
  }

  addToArchives(todo: Todo) {


    this.archivesService.saveTodo(todo, sessionStorage.getItem('emailId'), todo.categoryId).subscribe(data => {
      console.log(data);
      this.categoryListComponent.ngOnInit();
      this.ngOnInit();
      this.homeComponent.updateTodos(this.selectedCategory);
      this.snackBar.open("Todo Added to Archived", "X");

    })

    this.todoService.deleteTodo(sessionStorage.getItem('emailId'), todo.categoryId, todo.todoId).subscribe(data => {
      console.log(data);
      this.categoryListComponent.ngOnInit();
      this.ngOnInit();
      this.homeComponent.updateTodos(this.selectedCategory);


    })

  }

}
