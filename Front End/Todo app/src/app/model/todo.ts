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
    guest!: boolean;

    emailId!: string | null;
    categoryId!: number;

}
