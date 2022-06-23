import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { KnjigeService } from './knjige.service';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-pretraga',
  templateUrl: './pretraga.component.html',
  styleUrls: ['./pretraga.component.css']
})
export class PretragaComponent implements OnInit {

  knjigeZaPrikaz: any;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  constructor( private ruter: Router, knjigeService: KnjigeService, private snackBar: MatSnackBar, ) {
    this.knjigeZaPrikaz = [];
    if (history.state.vrsta == 1) 
      knjigeService.pretraga(history.state.podaci).subscribe(response => { this.knjigeZaPrikaz = response; this.prikaziKnjige(); });
    else if (history.state.vrsta == 2) 
      knjigeService.generisiTemplejt(history.state.podaci).subscribe(response => { this.knjigeZaPrikaz = response; this.prikaziKnjige(); });
    else
      knjigeService.staraPretraga().subscribe(response => { this.knjigeZaPrikaz = response; this.prikaziKnjige(); });
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
    
    divZaKnjige.setAttribute("style", "overflow-y: scroll;height:500px;");
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
    this.ruter.navigate(["/Knjiga"], {state: {data: {'knjiga': odabranaKnjiga}}});
  }

  ispisPoruke() {
    this.snackBar.open("Nema pronaženih knjiga :)", "x", {
      duration: 3000,
      verticalPosition: this.verticalPosition,
      panelClass: "back-green"
    });
  }

}
