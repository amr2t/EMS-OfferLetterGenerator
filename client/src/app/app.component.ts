import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { EcomService } from './ecom.service';
import { Product } from './product';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'client';
  public product!: Product[];

  constructor(private ecomService: EcomService) {}


  ngOnInit() {
    this.getProducts();
  }

  public getProducts() {
    this.ecomService.getProduct().subscribe({
      next: (response: Product[]) => {
        this.product = response;
      },
      error: (error: HttpErrorResponse) => {
         alert(error.message);
      }
    });
  }
}
