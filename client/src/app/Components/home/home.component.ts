import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../../Service/user-service.service';
import { AboutComponent } from '../about/about.component';
import { CartComponent } from '../cart/cart.component';
import { HomeproductComponent } from '../homeproduct/homeproduct.component';
import { MyprofileComponent } from '../myprofile/myprofile.component';
import { OrderComponent } from '../order/order.component';
import { OrderslistComponent } from '../orderslist/orderslist.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  
  public loggedIn = false;
  dummycomponent:any;
  constructor(private _service: UserServiceService) { 
    this.loggedIn=this._service.isLoggedIn();
    this.dummycomponent = HomeproductComponent;
    // this._service.getProductFromRemote().subscribe(
    //   data => {
    //     console.log(data)

    //     data.forEach((i:any) => {
    //       this.product.push(new Product(i.id,i.headline,i.description,i.price,i.imageurl));
    //     });

    //     console.log(this.product)
    //   },
    //   error => {
    //     console.log(error);
    //   }
      
    // )
  }

  ngOnInit(): void {
  }

  assignItem(component: string){
    if(component==='homeproduct'){
      this.dummycomponent = HomeproductComponent;
    }else if(component === 'myprofile'){
      this.dummycomponent = MyprofileComponent;
    }else if(component === 'cart'){
      this.dummycomponent = CartComponent;
    }else if(component === 'order'){
      this.dummycomponent = OrderComponent;
    }else if(component === 'about'){
      this.dummycomponent = AboutComponent;
    }
  }
  onLogout(){
    this.loggedIn=false;
    localStorage.clear();
  }
}
