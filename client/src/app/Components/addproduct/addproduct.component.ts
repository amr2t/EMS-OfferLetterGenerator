import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/Entity/product';
import { UserServiceService } from 'src/app/Service/user-service.service';

@Component({
  selector: 'app-addproduct',
  templateUrl: './addproduct.component.html',
  styleUrls: ['./addproduct.component.css']
})
export class AddproductComponent implements OnInit {
  prod!: Product;
  headline!: string;
  description!: string;
  price!: number;
  quantity!: number;
  imageurl!: string;
  constructor(private _service:UserServiceService,private _router:Router) { }

  ngOnInit(): void {
  }
  onSubmit(){
    this.prod = new Product("",this.headline,this.description,this.price,this.imageurl,this.quantity);
    console.log(this.prod);
    this._service.addProductToRemote(this.prod).subscribe(
      data => {
        this._router.navigate(['productslist']);
        console.log(data);
      },
      error => {
        console.log(error);
      }
    )
  }
}
