import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { Category } from 'src/app/model/category';
import { Todo } from 'src/app/model/todo';
import { ArchivesServiceService } from 'src/app/service/archivesService/archives-service.service';
import { ImageUploadingService } from 'src/app/service/image/image-uploading.service';
import { TodoServiceService } from 'src/app/service/todoService/todo-service.service';
import { CategoryListComponent } from '../../category-list/category-list.component';
import { CreateTodoComponent } from '../../create-todo/create-todo.component';
import { HomeComponent } from '../../home.component';


@Component({
  selector: 'app-completed',
  templateUrl: './completed.component.html',
  styleUrls: ['./completed.component.css']
})
export class CompletedComponent implements OnInit, OnChanges {

  archived!: Todo[];

  constructor(private todoService: TodoServiceService, private dialog: MatDialog, private archivesService: ArchivesServiceService, private categoryListComponent: CategoryListComponent, private homeComponent: HomeComponent, private modalService: NgbModal, private imageUploadingService: ImageUploadingService, private snackBar: MatSnackBar) {
    console.log("completed constructor");

    this.todos = [];
  }

  ngOnInit(): void {
    console.log("completed ngOnINit");
    // this.homeComponent.ngOnInit();

    this.todos = [];
    this.todosToDisplay?.map(element => {
      if (element.completed == true) {
        this.todos.push(element);
      }
    })

    this.fileInfos = this.imageUploadingService.getFiles();

    this.fileInfos.subscribe(data => {
      console.log(data);
      
    })


  }

  fileInfos?: Observable<any>;

  ngOnChanges() {
    console.log("completed ngOnChanges");

    this.todos = [];

    this.todosToDisplay?.map(element => {
      if (element.completed == true) {
        this.todos.push(element);
      }
    })

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
      console.log(data);
      this.homeComponent.updateTodos(this.selectedCategory);
      this.snackBar.open("Todo Updated Successfully", "X");

    })

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
