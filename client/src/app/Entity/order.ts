import { OrderItem } from "./order-item";
import { Product } from "./product";

export class Order {
    id!:string;
    orderDate!:Date;
    orderItems!:OrderItem[];
    totalPay!:number;

    constructor(id:string,date:Date,items:OrderItem[],total:number){
        this.id=id;
        this.orderDate=date;
        this.orderItems=items;
        this.totalPay=total;
    }
}
