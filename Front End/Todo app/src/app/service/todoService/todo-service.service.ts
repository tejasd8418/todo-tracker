import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { User } from '../../model/user';
import { Observable } from 'rxjs';
import { Category } from 'src/app/model/category';
import { Guest } from 'src/app/model/guest';
import { Todo } from 'src/app/model/todo';

@Injectable({
  providedIn: 'root'
})
export class TodoServiceService {

  private baseUrl = 'http://localhost:9000';

  constructor(private http: HttpClient) { }

  registerUser(user: any){
    return this.http.post<User>(this.baseUrl + '/api/v1/register/', user)
  }

  guestCompletedTask(notification: any){
    return this.http.post<User>('http://localhost:8082/api/v3/notificationservice/add', notification);
  }

  getAllCategories(emailId: any): Observable<any[]>{
    return this.http.get<User[]>(this.baseUrl + '/api/v1/user/categories/' + emailId); 
  }

  deleteCategories(emailId: any, categoryId: any){
    return this.http.delete<User>(this.baseUrl + '/api/v1/user/' + emailId + "/" + categoryId);
  }

  saveCategory(emailId: any, category: any){
    return this.http.post<User>(this.baseUrl + '/api/v1/user/category/' + emailId, category);
  }

  updateCategory(emailId:any, category: Category){
    return this.http.put<User>(this.baseUrl + '/api/v1/user/updatecategory/' + emailId, category);
  }

  getAllTodos(emailId: any, categoryId: any): Observable<User[]>{
    return this.http.get<User[]>(this.baseUrl + '/api/v1/user/todos/' + emailId + '/' + categoryId); 
  }

  deleteTodo(emailId: any, categoryId: any, todoId: any){
    return this.http.delete<User>(this.baseUrl + '/api/v1/user/deletetodo/' + emailId + '/' + categoryId + '/' + todoId);
  }

  saveTodo( todo:any, emailid:any ,categoryid:any ){
    console.log(todo);
    
    return this.http.post<User>(this.baseUrl + '/api/v1/user/todo/' + emailid + '/' + categoryid , todo);
  }

  updateTodo( todo:any, emailid:any ,categoryid:any  ){
    return this.http.post<User>(this.baseUrl + '/api/v1/user/updatetodo/' + emailid + '/' + categoryid, todo);
  }

  addGuest(guest: Guest, emailId: any, categoryId: any, todoId: any){
    return this.http.post<User>(this.baseUrl + '/api/v1/user/addguest/' + emailId + '/' + categoryId + '/' + todoId, guest); 
  }




}
