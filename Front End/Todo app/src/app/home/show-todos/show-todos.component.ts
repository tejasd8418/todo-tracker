import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { Category } from 'src/app/model/category';
import { Todo } from 'src/app/model/todo';
import { ArchivesServiceService } from 'src/app/service/archivesService/archives-service.service';
import { ArchivesComponent } from './archives/archives.component';

@Component({
  selector: 'app-show-todos',
  templateUrl: './show-todos.component.html',
  styleUrls: ['./show-todos.component.css']
})
export class ShowTodosComponent implements OnInit, OnChanges {
  archived: any;
  
  @Input()
  archivesToDisplay!: Todo[]; 

  // @Output()
  // changeMade = new EventEmitter();

  @Output()
  selectionChanged = new EventEmitter<Todo>();

  @Input()
  selectedCategory!: Category;

  @Input()
  refresh!: string;

  
  @Input()
  todosToDisplay!: Todo[];
  todos: any;
  selectedTodo!: Todo;



  constructor(private archivesService: ArchivesServiceService) { }

  ngOnInit(): void {
  }

  ngOnChanges(): void {
    this.archived = [];
    this.archivesToDisplay = [];
  //   this.archivesToDisplay?.map(element=>{
  //     this.archived.push(element);
  //     console.log(this.archived);
      
  // })

  this.archivesService.getAllTodos(sessionStorage.getItem("emailId"), sessionStorage.getItem("categoryId")).subscribe(data=>{
    console.log(sessionStorage.getItem("categoryId"));
    
    console.log(data);
    this.archivesToDisplay = data;
    
  })

  this.todos = [];

    this.todosToDisplay?.map(element => {
      if (element.completed == false) {
        this.todos.push(element);
      }
    })
  }

  // active = 1;

  onSelectionChanged(selectedTodo: Todo) {
    this.selectedTodo = selectedTodo;
    console.log(this.selectedTodo);
    console.log(selectedTodo);
  }

  // onChangeMade(event: string) {
  //   this.changeMade = event;
  // }

  todoTab(){
    this.ngOnChanges();
  }

  completedTab(){
    this.ngOnChanges();
  }

  archivesTab(){
    this.ngOnChanges();
  }

}
