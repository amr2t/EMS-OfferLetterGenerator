import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserServiceService } from './Service/user-service.service';

@Injectable()
export class TokenInterceptorInterceptor implements HttpInterceptor {

  constructor(private _service:UserServiceService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let newReq = request;
    let token = this._service.getToken();
    // console.log("before setting header");
    // console.log(newReq,token);
    
    
    if(token!=null){
      newReq = newReq.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      })
    }
    // console.log("after setting header");
    // console.log(newReq,token);
    return next.handle(newReq);
  }
}
