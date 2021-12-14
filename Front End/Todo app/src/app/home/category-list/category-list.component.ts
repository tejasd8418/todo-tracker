import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSelectionListChange } from '@angular/material/list';
import { ActivatedRoute, Router } from '@angular/router';
import { CreateCategoryComponent } from 'src/app/home/category-list/create-category/create-category.component';
import { Category } from 'src/app/model/category';
import { TodoServiceService } from 'src/app/service/todoService/todo-service.service';
import { HomeComponent } from '../home.component';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit, OnChanges {

  categories: Category[] = [];
  // categories: Category[] = [{categoryId: 1, categoryName: "cat1"}, {categoryId: 2, categoryName: "cat2"} ];

  constructor(private dialog: MatDialog, private todoService: TodoServiceService, private route: ActivatedRoute, private router: Router, private homeComponent: HomeComponent) { }

  ngOnInit(): void {
    this.categories = this.displayCategories;
  }

  ngOnChanges(): void {

  }

  @Output()
  selectedCategory = new EventEmitter();

  @Input()
  displayCategories!: Category[];

  newCategory(): void {

    const dialogRef = this.dialog.open(CreateCategoryComponent, {

      width: '1000px',
      // disableClose: true ,  
    });

    dialogRef.afterClosed().subscribe(data => {
      console.log(data);
      this.homeComponent.ngOnInit();
    })
    // this.router.navigate(['createCategory']);
  }

  onSelectionChange(event: MatSelectionListChange) {
    console.log(event.source.selectedOptions.selected[0].value);
    this.selectedCategory.emit(event.source.selectedOptions.selected[0].value);
  }

  editCategory(category: Category) {
    const dialogRef = this.dialog.open(CreateCategoryComponent, {
      width: '1000px',
      data: category

    });

    dialogRef.afterClosed().subscribe(data => {
      console.log(data);
      this.homeComponent.ngOnInit();
    })
  }

  deleteCategory(category: Category) {
    this.todoService.deleteCategories("tejas@gmail.com", category.categoryId).subscribe(data => {
      console.log(data);
      this.homeComponent.ngOnInit();
    })
  }
}
