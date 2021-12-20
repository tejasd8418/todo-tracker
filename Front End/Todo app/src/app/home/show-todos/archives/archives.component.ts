import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from 'firebase/auth';
import { Observable } from 'rxjs';
import { Category } from 'src/app/model/category';
import { Todo } from 'src/app/model/todo';
import { ArchivesServiceService } from 'src/app/service/archivesService/archives-service.service';
import { ImageUploadingService } from 'src/app/service/image/image-uploading.service';
import { TodoServiceService } from 'src/app/service/todoService/todo-service.service';
import { CategoryListComponent } from '../../category-list/category-list.component';
import { HomeComponent } from '../../home.component';

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
  todoView!: Todo;
  fileInfos?: Observable<any>; 

  constructor(private archivesService: ArchivesServiceService, private todoService: TodoServiceService, private categoryListComponent: CategoryListComponent, private homeComponent: HomeComponent, private modalService: NgbModal, private imageUploadingService: ImageUploadingService, private snackBar: MatSnackBar) {
    console.log("archives constructor");

    this.archived = [];
    console.log("constructor");
    
   }

  archived!: Todo[];

  ngOnInit(): void {
    console.log("archives ngOnInit");
    // this.homeComponent.ngOnInit();

    console.log(this.selectedCategory);
    this.archived = [];
    this.archivesToDisplay?.map(element=>{
      this.archived.push(element);
      console.log(this.archived);
      
  })

  this.fileInfos = this.imageUploadingService.getFiles();

    this.fileInfos.subscribe(data => {
      console.log(data);
      
    })

  


  }

  ngOnChanges(){
    console.log("archives ngOnChanges");

    this.archived = [];
    this.archivesToDisplay?.map(element=>{
      this.archived.push(element);
      console.log(this.archived);
      
  })
}

displayTodo(longContent: any, todo: Todo){
  this.todoView = todo;
  this.modalService.open(longContent, { scrollable: true });
}



  removeFromArchives(archiveTask: Todo){
    this.archivesService.deleteTodo(sessionStorage.getItem('emailId'), archiveTask.categoryId, archiveTask.todoId).subscribe(data=>{
      console.log(data);
      this.categoryListComponent.ngOnInit();
      this.ngOnInit();
      this.homeComponent.updateArchives(this.selectedCategory);
      this.snackBar.open("Todo Removed from Archives", "X");

      
    });

    this.todoService.saveTodo(archiveTask, sessionStorage.getItem('emailId'), archiveTask.categoryId).subscribe(data=>{
      console.log(data);
      this.categoryListComponent.ngOnInit();
      this.ngOnInit();
      this.homeComponent.updateTodos(this.selectedCategory);
      
    })
  }

  deleteTodo(archiveTask: Todo){
    this.archivesService.deleteTodo(sessionStorage.getItem('emailId'), archiveTask.categoryId, archiveTask.todoId).subscribe(data=>{
      console.log(data);
      this.ngOnInit();
      this.categoryListComponent.ngOnInit();
      this.ngOnInit();
      this.homeComponent.updateArchives(this.selectedCategory);
      this.snackBar.open("Todo Deleted Successfully", "X");

    })
  }


}
