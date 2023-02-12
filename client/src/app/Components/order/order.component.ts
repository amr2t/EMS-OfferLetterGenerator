import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/Entity/order';
import { OrderItem } from 'src/app/Entity/order-item';
import { UserServiceService } from 'src/app/Service/user-service.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  orders: Order[] = [];
  tempProd: OrderItem[] = [];
  totalorders!:number;
  constructor(private _service:UserServiceService) {
    
   }

  ngOnInit(): void {
    this._service.getOrdersFromRemote(this._service.getUserid()).subscribe(
      data =>{
        console.log(data);
        data.forEach((i:any) => {
          i.orderItems.forEach((j:any) => {
            this.tempProd.push(new OrderItem(j.id,j.prodHeadline,j.prodDescription,j.prodImageUrl,j.prodPrice,j.prodQuantity))
            
          });
          this.orders.push(new Order(i.id,i.orderDate,this.tempProd,i.totalPay));
          this.tempProd=[];
        });
        this.totalorders = this.orders.length;
      },
      error => {
        console.log(error);
      }
    )
  }

}
