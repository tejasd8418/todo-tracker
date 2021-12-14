import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Category } from 'src/app/model/category';
import { Todo } from 'src/app/model/todo';

@Component({
  selector: 'app-in-progress',
  templateUrl: './in-progress.component.html',
  styleUrls: ['./in-progress.component.css']
})
export class InProgressComponent implements OnInit {

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
