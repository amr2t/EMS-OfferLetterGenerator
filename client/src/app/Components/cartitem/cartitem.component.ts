import { Component, Input, OnInit } from '@angular/core';
import { Order } from 'src/app/Entity/order';
import { Product } from 'src/app/Entity/product';

@Component({
  selector: 'app-cartitem',
  templateUrl: './cartitem.component.html',
  styleUrls: ['./cartitem.component.css']
})
export class CartitemComponent implements OnInit {
  @Input() cartItem!: Product;
  @Input() order!: Order;
  constructor() { }

  ngOnInit(): void {
  }

}
