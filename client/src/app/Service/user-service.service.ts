import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../Entity/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Product } from '../Entity/product';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  tempObj = {
    username: "",
    password: ""
  };

  
  constructor(private _http: HttpClient) { }

  public loginUserFromRemote(user: User):Observable<any>{
    this.tempObj.username=user.email
    this.tempObj.password=user.password
    return this._http.post<any>("http://localhost:8080/authenticate",this.tempObj);
  }

  public registerUserFromRemote(user: User):Observable<any>{
    return this._http.post<any>("http://localhost:8080/register",user);
  }

  public getProductFromRemote():Observable<any>{
    return this._http.get<any>("http://localhost:8080/getProducts");
  }

  public isLoggedIn(){
    const token = localStorage.getItem("token");
    if(token===''||token==null){
      return false;
    }else{
      return true;
    }
  }

  public getToken(){
    return localStorage.getItem('token');
  }

  public getUserid(){
    return localStorage.getItem('userid');
  }

  public getRole(){
    return localStorage.getItem('roles');
  }

  public getCartItemFromRemote(userid:string|null):Observable<any>{
    return this._http.get<any>(`http://localhost:8080/${userid}/getCart`)
  }

  public getProfileFromRemote(userid:string|null):Observable<any>{
    return this._http.get<any>(`http://localhost:8080/${userid}/getUser`);
  }

  public getOrdersFromRemote(userid:string|null):Observable<any>{
    return this._http.get<any>(`http://localhost:8080/${userid}/getAllOrders`);
  }

  public getAllProductsFromRemote():Observable<any>{
    return this._http.get<any>("http://localhost:8080/getProducts");
  }

  public getAllOrdersFromRemote():Observable<any>{
    return this._http.get<any>("http://localhost:8080/getOrders");
  }

  public getAllUsersFromRemote():Observable<any>{
    return this._http.get<any>("http://localhost:8080/getUsers");
  }

  public addProductToRemote(prod:Product):Observable<any>{
    return this._http.post<any>("http://localhost:8080/addProduct",prod);
  }

  public getOrderSummaryFromRemote(userid:string|null):Observable<any>{
    return this._http.get<any>(`http://localhost:8080/${userid}/paymentSuccessful`);
  }
}
