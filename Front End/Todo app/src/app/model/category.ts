import { Todo } from "./todo";


export class Category {

    categoryId!: number;
    categoryName!: string;

    todos!: Todo[]
    emailId!: string;

    constructor(categoryName: string){
        this.categoryName = categoryName;
    }
}
