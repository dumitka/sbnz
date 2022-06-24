import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { KnjigeService } from './knjige.service';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { LoginService } from '../logovanje/login.service';

@Component({
  selector: 'app-prikaz-knjige',
  templateUrl: './prikaz-knjige.component.html',
  styleUrls: ['./prikaz-knjige.component.css']
})
export class PrikazKnjigeComponent implements OnInit {

  knjiga: any;
  pisci: String = "";
  zanrovi:String = "";
  knjigeZaPrikaz: any;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  constructor(private ruter: Router, private knjigeService: KnjigeService, 
      private snackBar: MatSnackBar, private loginService: LoginService,) {
    this.knjiga = history.state.knjiga;
    this.inicijalizacija();
   }

  inicijalizacija() {
    this.pisci = "";
    this.zanrovi = "";
    let korisnik = this.loginService.getTokenData()?.username;
    for (let i = 0; i < this.knjiga.pisci.length; i++)
      this.pisci += i == this.knjiga.pisci.length - 1 ? this.knjiga.pisci[i] : this.knjiga.pisci[i] + ", ";
    for (let i = 0; i < this.knjiga.zanrovi.length; i++)
      this.zanrovi += i == this.knjiga.zanrovi.length - 1 ? this.knjiga.zanrovi[i] : this.knjiga.zanrovi[i] + ", ";
    this.knjigeService.preporuka(korisnik, this.knjiga.isbn).subscribe(response => { this.knjigeZaPrikaz = response; this.prikaziKnjige(); });
  }

  ngOnInit(): void {
  }

  nazadNaProfil() {
    this.ruter.navigate(['/Profil']);
  }

  
  prikaziKnjige() {
    if (this.knjigeZaPrikaz.length == 0) {
      this.ispisPoruke();
      return;
    }
    let divZaKnjige = document.getElementById("divZaKnjige");
    if (divZaKnjige == null) return;
    // brisemo sve postojece
    while (divZaKnjige.firstChild) {
      if (divZaKnjige.lastChild != null) 
        divZaKnjige.removeChild(divZaKnjige.lastChild);
    }
    
    divZaKnjige.setAttribute("style", "overflow-y: scroll;height:450px;");
    let redniBr = 0;
    this.knjigeZaPrikaz.forEach((knjiga: any) => {
      let div = document.createElement("div");
      div.className = "mat-card";
      div.setAttribute("style", "margin-left: 31px; margin-top: 15px;width:300px;float: left;");
      div.setAttribute("name", knjiga.isbn);
      div.setAttribute("id", "divKnjige" + knjiga.isbn);

      let div2 = document.createElement("div");
      div2.className = "mat-card-title-group";
      let ime = document.createElement("div");
      ime.className = "mat-card-title";
      ime.appendChild(document.createTextNode(knjiga.naziv));
      div2.appendChild(ime);
      let slika = document.createElement("img");
      slika.setAttribute("class", "mat-card-lg-image");
      slika.setAttribute("src", "assets\\knjiga.avif");
      slika.setAttribute("name", knjiga.isbn);
      slika.setAttribute("id", "slikaKnjige" + knjiga.id);
      slika.ondblclick = (e: any) => {this.dupliKlikNaKnjigu(e);};
      div2.appendChild(slika);
      div.appendChild(div2);
      // ----------
      let div3 = document.createElement("div");
      div3.className = "mat-card-content";
      let i = document.createElement("i");
      i.setAttribute("class", "sayings");
      let zaPisce = "";
      for (let i = 0; i < knjiga.pisci.length; i++) {
        zaPisce += i == knjiga.pisci.length - 1 ? knjiga.pisci[i] : knjiga.pisci[i] + " ,";
      }
      i.appendChild(document.createTextNode(zaPisce));
      div3.appendChild(i);
      div.appendChild(div3);

      if (divZaKnjige != null) divZaKnjige.appendChild(div);
      redniBr++;
    });
  }

  dupliKlikNaKnjigu(e: any) {
    let odabranaKnjiga;
    for (let elem of this.knjigeZaPrikaz) {
      if (elem.isbn == e.srcElement.name) {
        odabranaKnjiga = elem;
        break;
      }
    }
    this.knjiga = odabranaKnjiga;
    this.inicijalizacija();
  }
  
  ispisPoruke() {
    this.snackBar.open("Nema knjiga za preporuku :)", "x", {
      duration: 3000,
      verticalPosition: this.verticalPosition,
      panelClass: "back-green"
    });
  }
}
