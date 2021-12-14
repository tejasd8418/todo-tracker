import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { User } from 'firebase/auth';
import { Category } from 'src/app/model/category';
import { Todo } from 'src/app/model/todo';
import { ArchivesServiceService } from 'src/app/service/archivesService/archives-service.service';
import { TodoServiceService } from 'src/app/service/todoService/todo-service.service';
import { CategoryListComponent } from '../category-list/category-list.component';
import { HomeComponent } from '../home.component';

@Component({
  selector: 'app-archives',
  templateUrl: './archives.component.html',
  styleUrls: ['./archives.component.css']
})
export class ArchivesComponent implements OnInit, OnChanges {

  @Output()
  selectionChanged = new EventEmitter<Todo>();

  @Input()
  selectedCategory!: Category;

  @Input()
  refresh!: string;

  @Input()
  archivesToDisplay!: Todo[]; 

  @Output()
  changeMade = new EventEmitter();

  constructor(private archivesService: ArchivesServiceService, private todoService: TodoServiceService, private categoryListComponent: CategoryListComponent, private homeComponent: HomeComponent) {
    this.archived = [];
    console.log("constructor");
    
   }

  archived!: Todo[];

  ngOnInit(): void {
    console.log("ngOnInit");
    
    console.log(this.selectedCategory);
    this.archived = [];
    this.archivesToDisplay?.map(element=>{
      this.archived.push(element);
      console.log(this.archived);
      
  })

  
    
    // this.archivesService.getAllTodos("tejas@gmail.com", 1).subscribe(data=>{
    //   this.archived = data;
    //   console.log(data);
    // })

  }

  ngOnChanges(){
  
    this.archived = [];
    this.archivesToDisplay?.map(element=>{
      this.archived.push(element);
      console.log(this.archived);
      
  })
}

  // updateTodo(archiveTask: Todo, selectedCategory: Category){
  //   this.archivesService.updateTodo(todo, todo.emailId, todo.categoryId).subscribe(data=>{
  //     console.log(data);
  //   })
  // }

  removeFromArchives(archiveTask: Todo){
    this.archivesService.deleteTodo(archiveTask.emailId, archiveTask.categoryId, archiveTask.todoId).subscribe(data=>{
      console.log(data);
      this.categoryListComponent.ngOnInit();
      this.ngOnInit();
      this.homeComponent.updateArchives(this.selectedCategory);
      
    });

    this.todoService.saveTodo(archiveTask, archiveTask.emailId, archiveTask.categoryId).subscribe(data=>{
      console.log(data);
      this.categoryListComponent.ngOnInit();
      this.ngOnInit();
      this.homeComponent.updateTodos(this.selectedCategory);
      
    })
  }

  deleteTodo(archiveTask: Todo){
    this.archivesService.deleteTodo(archiveTask.emailId, archiveTask.categoryId, archiveTask.todoId).subscribe(data=>{
      console.log(data);
      this.ngOnInit();
      this.categoryListComponent.ngOnInit();
      this.ngOnInit();
      this.homeComponent.updateArchives(this.selectedCategory);
      
    })
  }


}
