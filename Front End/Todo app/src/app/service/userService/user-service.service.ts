import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { User } from 'src/app/model/user';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient) { }

  private baseUrl = 'http://localhost:9000';

  registerUser(user: any) {
    console.log(user);

    return this.http.post<User>(this.baseUrl + '/api/v2/register/', user)
  }

  loginUser(user: any) {
    return this.http.post<any>(this.baseUrl + '/api/v2/login/', user)
      .pipe(map(userData => {
        sessionStorage.setItem("emailId", user.emailId);
        let tokenStr = "Bearer " + userData.token;
        sessionStorage.setItem("token", tokenStr);
        console.log(sessionStorage.getItem(tokenStr));
        
        return userData;
      })
    );
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem("emailId");
    console.log(user);
    
    console.log(!(user === null));
    return !(user === null);
  }

  logOut() {
    sessionStorage.removeItem("emailId");
  }
}
