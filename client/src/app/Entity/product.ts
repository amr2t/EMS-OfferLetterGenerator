export class Product {
    productid: string;
    headline: string;
    description: string;
    price: number;
    imageurl: string;
    stock: number;
    // constructor(){}

    constructor(id:string,headline:string,description:string,price:number,imageurl:string,stock:number){
        this.description=description;
        this.headline=headline;
        this.imageurl=imageurl;
        this.price=price;
        this.productid=id;
        this.stock=stock;
    }

}
