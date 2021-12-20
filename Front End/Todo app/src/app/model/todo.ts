import { Guest } from "./guest";
import { Image } from "./image";

export class Todo {

    todoId!: number;
    todoTitle!: string;
    todoContent!: string;
    imageUrl!: string;
    dueDate!: string;
    highPriority!: boolean;
    completed!: boolean;
    guests!: Guest[];

    emailId!: string;
    categoryId!: number;

}
