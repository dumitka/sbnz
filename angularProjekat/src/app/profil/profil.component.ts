import { Component, OnInit } from '@angular/core';
import { LoginService } from '../logovanje/login.service';
import { KorisnikService } from './korisnik.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { DijalogPretragaComponent } from '../dijalog-pretraga/dijalog-pretraga.component';
import { DijalogOdabirComponent } from '../dijalog-odabir/dijalog-odabir.component';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {
  ime:string = '';
  prezime:string = '';

  constructor(private loginService: LoginService, private korisnikServis: KorisnikService, private ruter: Router, 
      public dialog: MatDialog,) {
    this.korisnikServis.getInfo().subscribe((data:any) => {
      this.ime = data.ime; 
      this.prezime = data.prezime; 
    });
   }

  ngOnInit() {
  }

  odjava() { this.loginService.logout(); }
  izmena() { this.ruter.navigate(["/IzmenaPodataka"]); }

  pretraga() { 
    const dialogRef = this.dialog.open(DijalogPretragaComponent, {width: '500px',});

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        // obicna pretraga
        this.ruter.navigate(["/Pretraga"]);
      }
    });
  }

  naprednaPretraga() { 
    const dialogRef = this.dialog.open(DijalogOdabirComponent, {width: '500px',});

    dialogRef.afterClosed().subscribe(result => {
      if (result != null) {
        //alert("novaa pretraga");
        this.ruter.navigate(["/NaprednaPretraga"]);
        return;
      }
      //alert("izvrsi staru pretragu");
      this.ruter.navigate(["/NaprednaPretraga"]);
    }
    );
  }
}
