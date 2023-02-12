import { Component, OnInit, Input } from '@angular/core';
import { Product } from '../../Entity/product';

@Component({
  selector: 'app-cardproduct',
  templateUrl: './cardproduct.component.html',
  styleUrls: ['./cardproduct.component.css']
})
export class CardproductComponent implements OnInit {
  @Input() prod!: Product;

  constructor() {
    
   }

  ngOnInit(): void {
  }

}
