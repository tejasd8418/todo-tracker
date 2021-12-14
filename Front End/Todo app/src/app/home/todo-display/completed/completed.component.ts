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

}
