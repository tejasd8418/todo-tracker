import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/model/user';

@Injectable({
  providedIn: 'root'
})
export class GuestServiceService {

  
  private baseUrl = 'http://localhost:8086';

  constructor(private http: HttpClient) { }


  getAllCategories(emailId: any): Observable<User[]>{
    return this.http.get<User[]>(this.baseUrl + '/api/v5/user/categories/' + emailId); 
  }

  deleteCategories(emailId: any, categoryId: any){
    return this.http.delete<User>(this.baseUrl + '/api/v5/user/' + emailId + categoryId);
  }

  saveCategory(emailId: any, category: any){
    return this.http.post<User>(this.baseUrl + '/api/v5/user/category/' + emailId, category);
  }

  getAllTodos(emailId: any, categoryId: any): Observable<User[]>{
    return this.http.get<User[]>(this.baseUrl + '/api/v5/user/todos/' + emailId + '/' + categoryId ); 
  }

  deleteTodo(emailId: any, categoryId: any,todoId: any){
    return this.http.delete<User>(this.baseUrl + '/api/v5/user/deletetodo/' + emailId + '/' + categoryId + '/' + todoId);
  }

  saveTodo( todo:any, emailid:any ,categoryid:any ){
    return this.http.post<User>(this.baseUrl + '/api/v5/user/todo/' + emailid + '/' + categoryid , todo);
  }

  updateTodo( todo:any, emailid:any ,categoryid:any ){
    return this.http.post<User>(this.baseUrl + 'api/v5/user/updatetodo/' + emailid + '/' + categoryid, todo);
  }
}
