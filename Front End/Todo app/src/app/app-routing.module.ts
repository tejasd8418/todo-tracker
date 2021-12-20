import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountsComponent } from './accounts/accounts.component';
import { LoginComponent } from './accounts/login/login.component';
import { RegisterComponent } from './accounts/register/register.component';
import { AboutUsComponent } from './footer/about-us/about-us.component';
import { PrivacyPolicyComponent } from './footer/privacy-policy/privacy-policy.component';
import { CategoryListComponent } from './home/category-list/category-list.component';
import { CreateCategoryComponent } from './home/category-list/create-category/create-category.component';
import { CreateTodoComponent } from './home/create-todo/create-todo.component';
import { HomeComponent } from './home/home.component';
import { AuthGuardService } from './service/guards/auth-guard.service';

const routes: Routes = [
{
  path: "",
  canActivate: [AuthGuardService],
  component: HomeComponent,

},
{
  path: "about-us",
  component: AboutUsComponent
},
{
  path: "privacy-policy",
  component: PrivacyPolicyComponent
},
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
