import { Component, OnInit } from '@angular/core';
import { KorisniciService } from './korisnici.service';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-dodavanje-korisnika',
  templateUrl: './dodavanje-korisnika.component.html',
  styleUrls: ['./dodavanje-korisnika.component.css']
})
export class DodavanjeKorisnikaComponent implements OnInit {

  korisnikForm: any = null;
  RESPONSE_OK: number = 0;
  RESPONSE_ERROR: number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  constructor(private fb: FormBuilder, private korisniciServis: KorisniciService, private ruter: Router, 
      private snackBar: MatSnackBar, ) {
    this.korisnikForm = this.fb.group({
      ime: ['', Validators.required],
      prezime: ['', Validators.required],
      korIme: ['', Validators.required],
      loz: ['', Validators.required]
    });
   }

  ngOnInit(): void {
  }

  dodaj() {
    var korisnik = {
      'korisnickoIme': this.korisnikForm.getRawValue().korIme, 
      'lozinka': this.korisnikForm.getRawValue().loz, 
      'ime': this.korisnikForm.getRawValue().ime, 
      'prezime': this.korisnikForm.getRawValue().prezime }
    this.korisniciServis.dodajKorisnika(korisnik)
      .subscribe((data:any) => {
        this.ispisPoruke("Uspešno ste dodali korisnika " + korisnik.korisnickoIme + " :) ", this.RESPONSE_OK);
        this.ruter.navigate(['/ProfilAdmin']);
      },
      error => {
        if (error.status === 406) {
          this.ispisPoruke("Korisničko ime " + korisnik.korisnickoIme + " već postoji u sistemu :)", this.RESPONSE_ERROR);
        }});
  }

  public postojiGreska = (controlName: string, errorName: string) => {
    return this.korisnikForm.controls[controlName].hasError(errorName);
  }
  
  ispisPoruke(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }
}
