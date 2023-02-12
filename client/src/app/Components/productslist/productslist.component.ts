import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/Entity/product';
import { ProdListComponent } from 'src/app/Components/prod-list/prod-list.component';
import { UserServiceService } from 'src/app/Service/user-service.service';
import { OrderslistComponent } from '../orderslist/orderslist.component';
import { UserslistComponent } from '../userslist/userslist.component';

@Component({
  selector: 'app-productslist',
  templateUrl: './productslist.component.html',
  styleUrls: ['./productslist.component.css']
})
export class ProductslistComponent implements OnInit {
  
  dummyComponent:any;
  prodactive!:string;
  orderactive!:string;
  usersactive!:string;
  constructor(private _service:UserServiceService) { 
    
    this.dummyComponent = ProdListComponent;
  }

  ngOnInit(): void {
  }
  onLogout(){
    localStorage.clear();
    window.location.href="home";
  }

  assignComponent(component: string){
    if(component === 'prodList'){
      this.prodactive="active";
      this.orderactive="";
      this.usersactive="";
      this.dummyComponent = ProdListComponent;
    }
    else if(component === 'orderslist'){
      this.prodactive="";
      this.orderactive="active";
      this.usersactive="";
      this.dummyComponent = OrderslistComponent;
    }
    else if(component === 'userslist'){
      this.prodactive="";
      this.orderactive="";
      this.usersactive="active";
      this.dummyComponent = UserslistComponent;
    }
  }
}
