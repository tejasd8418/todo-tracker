import { Guest } from "./guest";
import { Image } from "./image";

export class Todo {

    todoId!: number;
    todoTitle!: string;
    todoContent!: string;
    image!: Image;
    dueDate!: string;
    isHighPriority!: boolean;
    completed!: boolean;
    guests!: Guest[];

    emailId!: string;
    categoryId!: number;

}
