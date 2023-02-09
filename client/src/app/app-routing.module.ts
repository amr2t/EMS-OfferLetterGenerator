import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductslistComponent } from './productslist/productslist.component';
import { CartComponent } from './cart/cart.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { OrderslistComponent } from './orderslist/orderslist.component';
import { UserslistComponent } from './userslist/userslist.component';
import { AddproductComponent } from './addproduct/addproduct.component';
import { UserordersComponent } from './userorders/userorders.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'cart', component: CartComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'productslist', component: ProductslistComponent },
  { path: 'orderslist', component: OrderslistComponent },
  { path: 'userslist', component: UserslistComponent },
  { path: 'addproduct', component: AddproductComponent },
  { path: 'orders', component: UserordersComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
