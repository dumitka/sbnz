import { Component, OnInit } from '@angular/core';
import { KnjigeService } from './knjige.service';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-dodaj-knjigu',
  templateUrl: './dodaj-knjigu.component.html',
  styleUrls: ['./dodaj-knjigu.component.css']
})
export class DodajKnjiguComponent implements OnInit {

  loginForm: any = null;
  RESPONSE_OK: number = 0;
  RESPONSE_ERROR: number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  constructor(private fb: FormBuilder, private knjigeServis: KnjigeService, private ruter: Router, 
      private snackBar: MatSnackBar, ) {
    this.loginForm = this.fb.group({
      isbn: ['', Validators.required],
      naziv: ['', Validators.required],
      izdavackaKuca: ['', Validators.required],
      pisci: ['', Validators.required],
      zanrovi: ['', Validators.required]
    });
   }

  ngOnInit(): void {
  }

  dodaj() {
    var listaPisaca = this.loginForm.getRawValue().pisci.split(';');
    var listaZanrova = this.loginForm.getRawValue().zanrovi.split(';');
    var knjiga = {
      'isbn': this.loginForm.getRawValue().isbn, 
      'naziv': this.loginForm.getRawValue().naziv, 
      'pisci': listaPisaca, 
      'zanrovi': listaZanrova, 
      'izdavackaKuca': this.loginForm.getRawValue().izdavackaKuca }
    this.knjigeServis.dodajKnjigu(knjiga)
      .subscribe((data:any) => {
        this.openSnackBar("Uspešno ste dodali knjigu " + knjiga.naziv + " :) ", this.RESPONSE_OK);
        this.ruter.navigate(['/ProfilAdmin']);
      },
      error => {
        if (error.status === 406) {
          this.openSnackBar("ISBN " + knjiga.isbn + " već postoji u sistemu :)", this.RESPONSE_ERROR);
        }});
  }

  public hasError = (controlName: string, errorName: string) => {
    return this.loginForm.controls[controlName].hasError(errorName);
  }
  
  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }
}
