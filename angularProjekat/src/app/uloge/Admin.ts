import { Injectable } from "@angular/core";
import { CanActivate } from "@angular/router";
import { LoginService } from "../logovanje/login.service";

@Injectable({
    providedIn: 'root'
  })
  export class Admin implements CanActivate {
  
    constructor(private authService: LoginService) { }
  
    canActivate() {
      if (this.authService.getTokenData()?.role === 'ADMIN') {
        return true;
      }
      return false;
    }
}