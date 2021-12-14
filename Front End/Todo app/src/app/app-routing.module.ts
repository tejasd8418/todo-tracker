import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountsComponent } from './accounts/accounts.component';
import { LoginComponent } from './accounts/login/login.component';
import { RegisterComponent } from './accounts/register/register.component';
import { CategoryListComponent } from './home/category-list/category-list.component';
import { CreateCategoryComponent } from './home/category-list/create-category/create-category.component';
import { CreateTodoComponent } from './home/create-todo/create-todo.component';
import { HomeComponent } from './home/home.component';
import { TodoComponent } from './home/todo/todo.component';

const routes: Routes = [{
  path: "",
  // canActivate: [ShouldBeLoginServiceService], 
  component: HomeComponent,
  // children: [
    // {
    //   path: "createcategory", component: CreateCategoryComponent,
    // },
    // {
    //   path: "createTodo", component: CreateTodoComponent,
    // },
  //   {
  //     path: "category", component: CategoryListComponent,
  //     children: [
  //       {
  //         path: "todo", component: TodoComponent
  //       },
  //     ]
  //   }
  // ]
},
// {
//   path: "createcategory", component: CreateCategoryComponent,
// },
// {
//   path: "createtodo", component: CreateTodoComponent,
// },
{
  path: "accounts", component: AccountsComponent,
  children: [
    {
      path: "", redirectTo: "login", pathMatch: "full"
    },
    {
      path: "login", component: LoginComponent
    },
    {
      path: "register", component: RegisterComponent
    }
  ]
}



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
