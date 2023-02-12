import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/Entity/product';
import { UserServiceService } from 'src/app/Service/user-service.service';

@Component({
  selector: 'app-prod-list',
  templateUrl: './prod-list.component.html',
  styleUrls: ['./prod-list.component.css']
})
export class ProdListComponent implements OnInit {
  prod: Product[] = [];
  constructor(private _service:UserServiceService) { 
    this._service.getAllProductsFromRemote().subscribe(
      data => {
        console.log(data);
        data.forEach((i: any) => {
          this.prod.push(new Product(i.id,i.headline,i.description,i.price,i.imageurl,i.stock));
        });
      },
      error => {
        console.log(error);
        
      }
    )
  }

  ngOnInit(): void {
  }

}
