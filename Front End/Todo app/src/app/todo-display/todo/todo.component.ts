import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CreateTodoComponent } from 'src/app/home/create-todo/create-todo.component';
import { Category } from 'src/app/model/category';
import { Todo } from 'src/app/model/todo';
import { ArchivesServiceService } from 'src/app/service/archivesService/archives-service.service';
import { TodoServiceService } from 'src/app/service/todoService/todo-service.service';
import { AddGuestComponent } from './add-guest/add-guest.component';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit, OnChanges {

  constructor(private todoService: TodoServiceService, private dialog: MatDialog, private archivesService: ArchivesServiceService) { 
    this.todos = [];
  }

  ngOnInit(): void {
    this.todos = [];
  }

  ngOnChanges(){
    this.todos = [];
    this.todosToDisplay.map(element=>{
        this.todos.push(element);
      
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

  statusId = 1;

  todos: Todo[] = [];

  deleteTodo(todo: Todo){
    console.log(todo);
    console.log(this.selectedCategory);
    this.todoService.deleteTodo("tejas@gmail.com",this.selectedCategory.categoryId, this.statusId, todo.todoId).subscribe(data=>{
      console.log(data);
    })
  }

  updateTodo(todo: Todo, selectedCategory: Category){
    console.log(todo);
    
    this.dialog.open(CreateTodoComponent, {
      width: '1000px',
      data: {todo, selectedCategory}
    });
  }

  addGuest(todo: Todo){
    this.dialog.open(AddGuestComponent, {
      width: '1000px',
       data: todo
    });
  }

  addToArchives(todo: Todo){
    this.archivesService.saveTodo(todo, todo.emailId, todo.categoryId, todo.statusId).subscribe(data=>{
      console.log(data);
    })
    this.todoService.deleteTodo(todo.emailId, todo.categoryId, todo.statusId, todo.todoId).subscribe(data=>{
      console.log(data);
    })

  }

}
