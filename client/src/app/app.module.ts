import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './Components/login/login.component';
import { SignupComponent } from './Components/signup/signup.component';
import { HomeComponent } from './Components/home/home.component';
import { CardproductComponent } from './Components/cardproduct/cardproduct.component';
import { CartComponent } from './Components/cart/cart.component';
import { CartitemComponent } from './Components/cartitem/cartitem.component';
import { ProductslistComponent } from './Components/productslist/productslist.component';
import { ProductslistitemComponent } from './Components/productslistitem/productslistitem.component';
import { OrderslistComponent } from './Components/orderslist/orderslist.component';
import { OrderslistitemComponent } from './Components/orderslistitem/orderslistitem.component';
import { UserslistComponent } from './Components/userslist/userslist.component';
import { UserslistitemComponent } from './Components/userslistitem/userslistitem.component';
import { AddproductComponent } from './Components/addproduct/addproduct.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpHeaderResponse, HttpHeaders, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { MyprofileComponent } from './Components/myprofile/myprofile.component';
import { AboutComponent } from './Components/about/about.component';
import { OrderComponent } from './Components/order/order.component';
import { AuthGuardGuard } from './Guards/auth-guard.guard';
import { TokenInterceptorInterceptor } from './token-interceptor.interceptor';
import { RoleGuardGuard } from './Guards/role-guard.guard';
import { UserServiceService } from './Service/user-service.service';
import { HomeproductComponent } from './Components/homeproduct/homeproduct.component';
import { ProdListComponent } from './Components/prod-list/prod-list.component';
import { OrderitemComponent } from './Components/orderitem/orderitem.component';
import { OrderSummaryComponent } from './Components/order-summary/order-summary.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    HomeComponent,
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
    ForbiddenComponent,
    MyprofileComponent,
    AboutComponent,
    OrderComponent,
    HomeproductComponent,
    ProdListComponent,
    OrderitemComponent,
    OrderSummaryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [UserServiceService,RoleGuardGuard,[{provide: HTTP_INTERCEPTORS,useClass:TokenInterceptorInterceptor,multi:true}]],
  bootstrap: [AppComponent]
})
export class AppModule { }
