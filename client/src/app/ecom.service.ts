import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from './product';


@Injectable({
  providedIn: 'root'
})
export class EcomService {

  constructor(private http: HttpClient) { }

  getProduct(): any{
    return this.http.get<Product[]>(
      'http://localhost:8080/getProducts'
    );
  }
}

