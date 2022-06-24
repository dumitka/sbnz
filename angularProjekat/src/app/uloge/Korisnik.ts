import { Injectable } from "@angular/core";
import { CanActivate } from "@angular/router";
import { LoginService } from "../logovanje/login.service";

@Injectable({
    providedIn: 'root'
  })
  export class Korisnik implements CanActivate {
  
    constructor(private authService: LoginService) { }
  
    canActivate() {
      if (this.authService.getTokenData()?.role === 'KORISNIK') {
        return true;
      }
      return false;
    }
}