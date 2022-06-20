import { Component, OnInit } from '@angular/core';
import { LoginService } from '../logovanje/login.service';
import { KorisnikService } from './korisnik.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {
  ime:string = '';
  prezime:string = '';

  constructor(private loginService: LoginService, private korisnikServis: KorisnikService, private ruter: Router, ) {
    this.korisnikServis.getInfo().subscribe((data:any) => {
      this.ime = data.ime; 
      this.prezime = data.prezime; 
    });
   }

  ngOnInit() {
  }

  odjava() { this.loginService.logout(); }
  izmena() { this.ruter.navigate(["/IzmenaPodataka"]); }
  pretraga() { this.ruter.navigate(["/Pretraga"]); }
  naprednaPretraga() { this.ruter.navigate(["/NaprednaPretraga"]); }
}
