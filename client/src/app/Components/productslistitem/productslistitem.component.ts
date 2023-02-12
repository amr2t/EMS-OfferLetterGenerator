import { Component, Input, OnInit } from '@angular/core';
import { Product } from 'src/app/Entity/product';

@Component({
  selector: 'app-productslistitem',
  templateUrl: './productslistitem.component.html',
  styleUrls: ['./productslistitem.component.css']
})
export class ProductslistitemComponent implements OnInit {
  @Input() prod!: Product;
  constructor() { }

  ngOnInit(): void {
  }

}
