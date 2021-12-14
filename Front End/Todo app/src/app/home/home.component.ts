import { BreakpointObserver } from '@angular/cdk/layout';
import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from '../model/category';
import { Todo } from '../model/todo';
import { TodoServiceService } from '../service/todoService/todo-service.service';
import { UserServiceService } from '../service/userService/user-service.service';
import { CreateTodoComponent } from './create-todo/create-todo.component';
import { MatDialog } from '@angular/material/dialog';
import { User } from '../model/user';
import { ArchivesComponent } from './archives/archives.component';
import { ArchivesServiceService } from '../service/archivesService/archives-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnChanges {



  selectedTodo!: Todo;
  selectedCategory!: Category;
  refresh!: string;
  todosToDisplay!: any;
  archivesToDisplay!: any;
  displayCategories!: Category[];
  changeMade: any = "no"; 

  constructor(private breakpointObserver: BreakpointObserver, private router: Router, private route: ActivatedRoute, private todoService: TodoServiceService, public dialog: MatDialog, private userService: UserServiceService, private archivesService: ArchivesServiceService) {
    this.todosToDisplay = [];
    this.displayCategories = [];
    console.log("home constructor");
  }


  ngOnChanges(): void {
    // this.todoService.getAllCategories("tejas@gmail.com").subscribe((data)=>{
    //   data.map((element)=>{
    //     console.log(element);
    //     this.displayCategories.push(element)
    //   });
    // })
  }


  ngOnInit(): void {
    this.displayCategories = [];
    this.todoService.getAllCategories("tejas@gmail.com").subscribe((data)=>{
      data.map((element)=>{
        console.log(element);
        this.displayCategories.push(element)
      });
    })
  }

  onSelectedCategory(category: Category) {
    this.selectedCategory = category;
    this.updateTodos(category);
    this.updateArchives(category);
  }

  updateTodos(category: Category){
    this.todoService.getAllTodos("tejas@gmail.com",category.categoryId).subscribe(data=>{
      console.log(data);
      this.todosToDisplay = data;
    });
  }

  updateArchives(category: Category){
    this.archivesService.getAllTodos("tejas@gmail.com",category.categoryId).subscribe(data=>{
      console.log(data);
      this.archivesToDisplay = data;
    });
  }

  onSelectionChanged(selectedTodo: Todo) {
    this.selectedTodo = selectedTodo;
    console.log(this.selectedTodo);
    console.log(selectedTodo);
  }

  onChangeMade(event: string) {
    this.changeMade = event;
  }

  composeTodoAdd(todo: Todo) {
    console.log("make change in ngChanges");
    this.refresh = "yes";
    // this.updateScheduledEmail();
  }

  
  createTodo(selectedCategory: Category){
    console.log(selectedCategory);
      const dialog1 = this.dialog.open(CreateTodoComponent, {
        width: '1000px',
        data: selectedCategory
        // disableClose: true ,
  
      });

      dialog1.afterClosed().subscribe(data=>{
        console.log(data);
        this.updateTodos(this.selectedCategory);
      })

    
  }

  accountInfo(){
    
  }

}



