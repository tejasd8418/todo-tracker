import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CreateTodoComponent } from 'src/app/home/create-todo/create-todo.component';
import { Category } from 'src/app/model/category';
import { Todo } from 'src/app/model/todo';
import { ArchivesServiceService } from 'src/app/service/archivesService/archives-service.service';
import { TodoServiceService } from 'src/app/service/todoService/todo-service.service';
import { CategoryListComponent } from '../category-list/category-list.component';
import { HomeComponent } from '../home.component';
import { AddGuestComponent } from './add-guest/add-guest.component';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit, OnChanges {
  archived!: Todo[];

  constructor(private todoService: TodoServiceService, private dialog: MatDialog, private archivesService: ArchivesServiceService, private categoryListComponent: CategoryListComponent, private homeComponent: HomeComponent) { 
    this.todos = [];
  }

  ngOnInit(): void {
    this.todos = [];
    this.todosToDisplay?.map(element=>{
      if (element.completed == false) {
        this.todos.push(element);
      }
  })

    
  }

  ngOnChanges(){
  
    this.todos = [];

    this.todosToDisplay?.map(element=>{
      if (element.completed == false) {
        this.todos.push(element);
      }
  })

    // this.archivesService.getAllTodos("tejas@gmail.com", 4).subscribe(data=>{
    //   this.archived = data;
    //   console.log(data);
    // })
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

  deleteTodo(todo: Todo){
    console.log(todo);
    console.log(this.selectedCategory);
    this.todoService.deleteTodo("tejas@gmail.com",this.selectedCategory.categoryId, todo.todoId).subscribe(data=>{
      console.log(data);
      this.categoryListComponent.ngOnInit();
      this.ngOnInit();
      this.homeComponent.updateTodos(this.selectedCategory);
    })
  }

  updateTodo(todo: Todo, selectedCategory: Category){
    console.log(todo);
    
    const dialog1 = this.dialog.open(CreateTodoComponent, {
      width: '1000px',
      data: {todo, selectedCategory}
    });

      dialog1.afterClosed().subscribe(data=>{
        console.log(data);
        this.homeComponent.updateTodos(this.selectedCategory);
      })
    
  }

  complete(todo:Todo, selectedCategory: Category){
    todo.completed = true;
    this.todoService.updateTodo(todo, todo.emailId, todo.categoryId).subscribe(data=>{
      console.log(data);
      this.homeComponent.updateTodos(this.selectedCategory);

    })
  }

  addGuest(todo: Todo){
    this.dialog.open(AddGuestComponent, {
      width: '1000px',
       data: todo
    });
  }

  addToArchives(todo: Todo){


    this.archivesService.saveTodo(todo, todo.emailId, todo.categoryId).subscribe(data=>{
      console.log(data);
      this.categoryListComponent.ngOnInit();
      this.ngOnInit();
      this.homeComponent.updateTodos(this.selectedCategory);

    })

    this.todoService.deleteTodo(todo.emailId, todo.categoryId, todo.todoId).subscribe(data=>{
      console.log(data);
      this.categoryListComponent.ngOnInit();
      this.ngOnInit();
      this.homeComponent.updateTodos(this.selectedCategory);


    })

  }

}
