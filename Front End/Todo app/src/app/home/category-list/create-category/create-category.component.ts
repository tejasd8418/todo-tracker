import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Category } from 'src/app/model/category';
import { ArchivesServiceService } from 'src/app/service/archivesService/archives-service.service';
import { TodoServiceService } from 'src/app/service/todoService/todo-service.service';
import { CategoryListComponent } from '../category-list.component';

@Component({
  selector: 'app-create-category',
  templateUrl: './create-category.component.html',
  styleUrls: ['./create-category.component.css']
})
export class CreateCategoryComponent implements OnInit {

  constructor(private fb: FormBuilder, private todoService: TodoServiceService,  public dialogRef: MatDialogRef<CreateCategoryComponent>, public router: Router, @Inject(MAT_DIALOG_DATA) public data: Category, private archivesService: ArchivesServiceService) { }

  updateCategory = new EventEmitter();

  ngOnInit(): void {
    // console.log(this.data.categoryId);
    if (this.data != null) {
      this.createCategoryForm?.setValue(this.data);
    }
  }

  createCategoryForm = this.fb.group({
    categoryId: null,
    categoryName: [null, Validators.required],
    emailId: null,
    todos: null
  });

  onSubmit() {
    const newCategory = new Category(this.createCategoryForm.value.categoryName);
    console.log(newCategory);

    newCategory.emailId = "tejas@gmail.com";
    if (this.data?.categoryId != null) {
      newCategory.categoryId = this.data.categoryId;
      this.onClose();
    }

    if (newCategory.categoryId == null) {

      this.todoService.saveCategory(newCategory.emailId, newCategory).subscribe(data => {
        console.log(data);
        
      })

      // this.archivesService.saveCategory(newCategory.emailId, newCategory).subscribe(data=>{
      //   console.log(data);
      //   this.onClose();
      // })
      
    }
    else {
      this.todoService.updateCategory(newCategory.emailId, newCategory).subscribe(data => {
        console.log(data);
      })
    }

    this.saveArchivesCategory();

  }

  saveArchivesCategory(){
    const newCategory = new Category(this.createCategoryForm.value.categoryName);
    console.log(newCategory);

    newCategory.emailId = "tejas@gmail.com";
    if (this.data?.categoryId != null) {
      newCategory.categoryId = this.data.categoryId;
      this.onClose();
    }

    if (newCategory.categoryId == null) {

      this.archivesService.saveCategory(newCategory.emailId, newCategory).subscribe(data => {
        console.log(data);
        this.onClose();
      })  
    }
  }

  onClose() {
    this.createCategoryForm.reset();
    // this.categoryListComponent.ngOnInit(); 
    this.dialogRef.close();
    // this.router.navigateByUrl("");
  }

}
