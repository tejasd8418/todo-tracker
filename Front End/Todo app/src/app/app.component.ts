import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Message } from './class/message';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Todo App';

  // constructor(private msg: AngularFireMessaging, private http: HttpClient) { }


  // ngOnInit(): void {
  //   this.msg.onMessage((payload) => {
  //     // Get the data about the notification
  //     let notification = payload.notification;
  //     // Create a Message object and add it to the array
  //     this.messages.push({ title: notification.title, body: notification.body, iconUrl: notification.icon });
  //   });
  // }

  // messages: Array<Message> = [];

  // this.msg.requestToken.subscribe(token => {

  //   console.log(token);
  //   this.http.post('http://localhost:8080/notification', {
  //     target: token,
  //     title: 'hello world',
  //     message: 'First notification, kinda nervous',
  //   }).subscribe(() => { });

  //   this.http.post('http://localhost:8080/topic/subscription', {
  //     topic: 'weather',
  //     subscriber: token
  //   }).subscribe(() => { });

  // }, error => {

  //   console.log(error);

  // });
}




