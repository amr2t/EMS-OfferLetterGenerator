import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/Entity/order';
import { OrderItem } from 'src/app/Entity/order-item';
import { UserServiceService } from 'src/app/Service/user-service.service';

@Component({
  selector: 'app-order-summary',
  templateUrl: './order-summary.component.html',
  styleUrls: ['./order-summary.component.css']
})
export class OrderSummaryComponent implements OnInit {
  order!:Order;
  tempProd: OrderItem[] = [];
  constructor(private _service:UserServiceService) { 
    this._service.getOrderSummaryFromRemote(this._service.getUserid()).subscribe(
      data=>{
        console.log(data);
        
          data.orderItems.forEach((j:any) => {
            this.tempProd.push(new OrderItem(j.id,j.prodHeadline,j.prodDescription,j.prodImageUrl,j.prodPrice,j.prodQuantity))
            
          });
          let i = data;
          this.order = new Order(i.id,i.orderDate,this.tempProd,i.totalPay); 
      },
      error => {
        console.log(error);
        
      }
    )
  }

  ngOnInit(): void {
  }

}
