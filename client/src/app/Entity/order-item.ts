export class OrderItem {
    id!: string;
    prodHeadline!: string;
    prodDescription!: string;
    prodImageUrl!: string;
    prodPrice!: number;
    prodQuantity!: number;

    constructor(id:string,headline:string,description:string,imageurl:string,price:number,quantity:number){
        this.id=id;
        this.prodHeadline=headline;
        this.prodDescription=description;
        this.prodImageUrl=imageurl;
        this.prodPrice=price;
        this.prodQuantity=quantity;
    }
}
