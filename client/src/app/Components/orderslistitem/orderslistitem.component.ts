import { Component, Input, OnInit } from '@angular/core';
import { Order } from 'src/app/Entity/order';

@Component({
  selector: 'app-orderslistitem',
  templateUrl: './orderslistitem.component.html',
  styleUrls: ['./orderslistitem.component.css']
})
export class OrderslistitemComponent implements OnInit {
  @Input() order!:Order;
  constructor() { 
    
  }

  ngOnInit(): void {
  }

}
