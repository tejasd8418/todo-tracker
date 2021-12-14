import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/model/user';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient) { }

  private baseUrl = 'http://localhost:8085';

  registerUser(user: any){
    console.log(user);
    
    return this.http.post<User>(this.baseUrl + '/api/v2/register/', user)
  }

  loginUser(user: any){
    return this.http.post<User>(this.baseUrl + '/api/v2/login/', user)
  }
}
