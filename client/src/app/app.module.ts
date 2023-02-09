import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { HomeComponent } from './home/home.component';
import { NavComponent } from './nav/nav.component';
import { CardproductComponent } from './cardproduct/cardproduct.component';
import { CartComponent } from './cart/cart.component';
import { CartitemComponent } from './cartitem/cartitem.component';
import { ProductslistComponent } from './productslist/productslist.component';
import { ProductslistitemComponent } from './productslistitem/productslistitem.component';
import { OrderslistComponent } from './orderslist/orderslist.component';
import { OrderslistitemComponent } from './orderslistitem/orderslistitem.component';
import { UserslistComponent } from './userslist/userslist.component';
import { UserslistitemComponent } from './userslistitem/userslistitem.component';
import { AddproductComponent } from './addproduct/addproduct.component';
import { UserordersComponent } from './userorders/userorders.component';
import { UserordersitemComponent } from './userordersitem/userordersitem.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    HomeComponent,
    NavComponent,
    CardproductComponent,
    CartComponent,
    CartitemComponent,
    ProductslistComponent,
    ProductslistitemComponent,
    OrderslistComponent,
    OrderslistitemComponent,
    UserslistComponent,
    UserslistitemComponent,
    AddproductComponent,
    UserordersComponent,
    UserordersitemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
