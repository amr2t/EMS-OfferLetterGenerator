import { Component, Input, OnInit } from '@angular/core';
import { Order } from 'src/app/Entity/order';

@Component({
  selector: 'app-orderitem',
  templateUrl: './orderitem.component.html',
  styleUrls: ['./orderitem.component.css']
})
export class OrderitemComponent implements OnInit {
  @Input() order!:Order;
  constructor() { }

  ngOnInit(): void {
  }

}
