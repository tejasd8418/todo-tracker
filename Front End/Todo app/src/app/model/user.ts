import { Category } from "./category";

export class User {

    emailId!: string;
    userName!: string;
    password!: string;
    phoneNo!: string;
    categories!: Category[];

    public constructor(emailId:string, userName: string, password: string, phoneNo: string);

    public constructor(emailId:string, password: string);

    public constructor(...params: any[]) {

        if (params.length === 4) {
            console.log('Used constructor 1');
            return;
        }
        if (params.length === 2) {
            console.log('Used constructor 2');
            return;
        }
        throw new Error('Undefined constructor.');
    }

}
