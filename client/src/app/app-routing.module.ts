import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductslistComponent } from './Components/productslist/productslist.component';
import { CartComponent } from './Components/cart/cart.component';
import { HomeComponent } from './Components/home/home.component';
import { LoginComponent } from './Components/login/login.component';
import { SignupComponent } from './Components/signup/signup.component';
import { OrderslistComponent } from './Components/orderslist/orderslist.component';
import { UserslistComponent } from './Components/userslist/userslist.component';
import { AddproductComponent } from './Components/addproduct/addproduct.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { MyprofileComponent } from './Components/myprofile/myprofile.component';
import { AboutComponent } from './Components/about/about.component';
import { OrderComponent } from './Components/order/order.component';
import { RoleGuardGuard } from './Guards/role-guard.guard';
import { ProdListComponent } from './Components/prod-list/prod-list.component';
import { HomeproductComponent } from './Components/homeproduct/homeproduct.component';
import { OrderSummaryComponent } from './Components/order-summary/order-summary.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'homeproduct', component: HomeproductComponent},
  { path: 'cart', component: CartComponent, canActivate: [RoleGuardGuard] },
  { path: 'orderSummary', component: OrderSummaryComponent},
  { path: 'order', component: OrderComponent, canActivate: [RoleGuardGuard]},
  { path: 'about', component: AboutComponent},
  { path: 'myprofile', component: MyprofileComponent, canActivate: [RoleGuardGuard]},
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'forbidden', component: ForbiddenComponent},
  { path: 'productslist', component: ProductslistComponent },
  { path: 'prodList', component: ProdListComponent},
  { path: 'orderslist', component: OrderslistComponent },
  { path: 'userslist', component: UserslistComponent },
  { path: 'addproduct', component: AddproductComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
