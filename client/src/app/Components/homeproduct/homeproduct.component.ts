import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/Entity/product';
import { UserServiceService } from 'src/app/Service/user-service.service';

@Component({
  selector: 'app-homeproduct',
  templateUrl: './homeproduct.component.html',
  styleUrls: ['./homeproduct.component.css']
})
export class HomeproductComponent implements OnInit {
  product: Product[] = [];
  constructor(private _service:UserServiceService) { }

  ngOnInit(): void {
    this._service.getProductFromRemote().subscribe(
      data => {
        console.log(data)

        data.forEach((i:any) => {
          this.product.push(new Product(i.id,i.headline,i.description,i.price,i.imageurl,i.stock));
        });

        console.log(this.product)
      },
      error => {
        console.log(error);
      }
      
    )
  }

}
