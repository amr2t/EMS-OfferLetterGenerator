import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cardproduct',
  templateUrl: './cardproduct.component.html',
  styleUrls: ['./cardproduct.component.css']
})
export class CardproductComponent implements OnInit {
  price: number = 299;

  constructor() { }

  ngOnInit(): void {
  }

}
