import { Component, OnInit } from '@angular/core';
import { LoginService } from '../logovanje/login.service';
import { AdminService } from './admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profil-admin',
  templateUrl: './profil-admin.component.html',
  styleUrls: ['./profil-admin.component.css']
})
export class ProfilAdminComponent implements OnInit {
  ime:string = '';
  prezime:string = '';

  constructor(private loginService: LoginService, private adminService: AdminService, private ruter: Router, ) {
    this.adminService.getInfo().subscribe((data:any) => {
      this.ime = data.ime; 
      this.prezime = data.prezime; 
    });
   }

  ngOnInit(): void {
    this.adminService.getInfo().subscribe((data:any) => {
      this.ime = data.ime; 
      this.prezime = data.prezime; 
    });
  }

  odjava() { this.loginService.logout(); }
  izmena() { this.ruter.navigate(["/IzmenaPodataka"]); }
  dodajKorisnika() { this.ruter.navigate(["/DodajKorisnika"]); }
  dodajKnjigu() { this.ruter.navigate(["/DodajKnjigu"]); }
}
