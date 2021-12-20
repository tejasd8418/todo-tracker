import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from 'src/app/model/category';
import { Todo } from 'src/app/model/todo';
import { User } from 'src/app/model/user';

@Injectable({
  providedIn: 'root'
})
export class ArchivesServiceService {

  constructor(private http: HttpClient) { }

  private baseUrl = 'http://localhost:9000/api/v4/archivesservice';

  registerUser(user: any){
    return this.http.post<User>(this.baseUrl + '/register/', user)
  }

  getAllCategories(emailId: any): Observable<any[]>{
    return this.http.get<User[]>(this.baseUrl + '/archives/categories/' + emailId); 
  }

  deleteCategories(emailId: any, categoryId: any){
    return this.http.delete<User>(this.baseUrl + '/archives' + emailId + "/" + categoryId);
  }

  saveCategory(emailId: any, category: any){
    return this.http.post<User>(this.baseUrl + '/archives/category/' + emailId, category);
  }

 

  getAllTodos(emailId: any, categoryId: any): Observable<Todo[]>{
    return this.http.get<Todo[]>(this.baseUrl + '/category/todos/' + emailId + '/' + categoryId ); 
  }

  deleteTodo(emailId: any, categoryId: any, todoId: any){
    return this.http.delete<User>(this.baseUrl + '/user/deletetodo/' + emailId + '/' + categoryId + '/' + todoId);
  }

  saveTodo( todo:any, emailid:any ,categoryid:any ){
    return this.http.post<User>(this.baseUrl + '/archives/todo/' + emailid + '/' + categoryid , todo);
  }

  updateTodo( todo:any, emailid:any ,categoryid:any){
    return this.http.post<User>(this.baseUrl + '/user/updatetodo/' + emailid + '/' + categoryid , todo);
  }
}
