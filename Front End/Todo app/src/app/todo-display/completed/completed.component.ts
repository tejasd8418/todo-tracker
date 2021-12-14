import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Category } from 'src/app/model/category';
import { Todo } from 'src/app/model/todo';

@Component({
  selector: 'app-completed',
  templateUrl: './completed.component.html',
  styleUrls: ['./completed.component.css']
})
export class CompletedComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  
  @Output()
  selectionChanged = new EventEmitter<Todo>();

  @Input()
  selectedCategory!: Category;

  @Input()
  refresh!: string;

  @Input()
  todosToDisplay!: Todo[];

  @Output()
  changeMade = new EventEmitter();

  statusId = 3;

  todos = [
    {
      todoTitle: "todo 1",
      todoContent: "todo content",
      image: null,
      dueDate: null,
      isHighPriority: false,
      isCompleted: false
    },
    {
      todoTitle: "todo 2",
      todoContent: "todo content 2 todo content 2 todo c2todo content 2todo content 2todo content 2",
      image: null,
      dueDate: null,
      isHighPriority: false,
      isCompleted: true
    },
    {
      todoTitle: "todo 3",
      todoContent: "todo content 3",
      image: null,
      dueDate: null,
      isHighPriority: true,
      isCompleted: true
    },
    {
      todoTitle: "todo 3",
      todoContent: "todo content 3",
      image: null,
      dueDate: null,
      isHighPriority: true,
      isCompleted: true
    },
    {
      todoTitle: "todo 3",
      todoContent: "todo content 3 todo content 3todo content 3todo content 3todo content 3",
      image: null,
      dueDate: null,
      isHighPriority: true,
      isCompleted: true
    },
    {
      todoTitle: "todo 3",
      todoContent: "todo content 3",
      image: null,
      dueDate: null,
      isHighPriority: true,
      isCompleted: true
    }

  ];

}
