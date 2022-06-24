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

  knjigaForm: any = null;
  RESPONSE_OK: number = 0;
  RESPONSE_ERROR: number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  constructor(private fb: FormBuilder, private knjigeServis: KnjigeService, private ruter: Router, 
      private snackBar: MatSnackBar, ) {
    this.knjigaForm = this.fb.group({
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
    var listaPisaca = this.knjigaForm.getRawValue().pisci.split(';');
    var listaZanrova = this.knjigaForm.getRawValue().zanrovi.split(';');
    var knjiga = {
      'isbn': this.knjigaForm.getRawValue().isbn, 
      'naziv': this.knjigaForm.getRawValue().naziv, 
      'pisci': listaPisaca, 
      'zanrovi': listaZanrova, 
      'izdavackaKuca': this.knjigaForm.getRawValue().izdavackaKuca }
    this.knjigeServis.dodajKnjigu(knjiga)
      .subscribe((data:any) => {
        this.ispisPoruke("Uspešno ste dodali knjigu " + knjiga.naziv + " :) ", this.RESPONSE_OK);
        this.ruter.navigate(['/ProfilAdmin']);
      },
      error => {
        if (error.status === 406) {
          this.ispisPoruke("ISBN " + knjiga.isbn + " već postoji u sistemu :)", this.RESPONSE_ERROR);
        }});
  }

  public postojiGreska = (controlName: string, errorName: string) => {
    return this.knjigaForm.controls[controlName].hasError(errorName);
  }
  
  ispisPoruke(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }
}
