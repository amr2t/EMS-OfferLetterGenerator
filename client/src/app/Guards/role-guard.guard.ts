import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoleGuardGuard implements CanActivate {
  canActivate(){
    if(localStorage.getItem("roles")==="ROLE_USER"){
      return true;
    }else{
      window.location.href="forbidden";
      return false;
    }
  }
  
}
