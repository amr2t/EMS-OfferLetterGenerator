import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/Entity/product';
import { UserServiceService } from 'src/app/Service/user-service.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  userid = localStorage.getItem('userid');
  items:any;
  total=0;
  cartItems: Product[] = [];
  constructor(private _service:UserServiceService) { 
    this._service.getCartItemFromRemote(this.userid).subscribe(
      data => {
        console.log(data);
        this.items = data.item;
        console.log(this.items);
        
        this.items.forEach((i:any) => {
          let t = i.product;
          this.cartItems.push(new Product(t.id,t.headline,t.description,t.price,t.imageurl,t.stock))
          this.total = this.total + t.price;
        });
      },
      error => {
        console.log(error);
      }
      
    )
  }

  ngOnInit(): void {
  }

  onCheckout(){
    window.location.href="orderSummary";
  }

}
