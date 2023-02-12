import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/Entity/order';
import { OrderItem } from 'src/app/Entity/order-item';
import { Product } from 'src/app/Entity/product';
import { UserServiceService } from 'src/app/Service/user-service.service';

@Component({
  selector: 'app-orderslist',
  templateUrl: './orderslist.component.html',
  styleUrls: ['./orderslist.component.css']
})
export class OrderslistComponent implements OnInit {
  orders: Order[] = [];
  tempProd: OrderItem[] = [];
  constructor(private _service:UserServiceService) {
    this._service.getAllOrdersFromRemote().subscribe(
      data =>{
        console.log(data);
        console.log("---------------frontend------------")
        data.forEach((i:any) => {
          i.orderItems.forEach((j:any) => {
            this.tempProd.push(new OrderItem(j.id,j.prodHeadline,j.prodDescription,j.prodImageUrl,j.prodPrice,j.prodQuantity))
            
          });
          console.log(this.tempProd);
          console.log("------item-------");
          this.orders.push(new Order(i.id,i.orderDate,this.tempProd,i.totalPay));
          this.tempProd=[];
        });
        console.log(this.orders);
        console.log("-------order----------")
      },
      error => {
        console.log(error);
        
      }
    )
   }

  ngOnInit(): void {
  }
  
}
