import { Component, OnInit } from '@angular/core';
import { KorisniciService } from './korisnici.service';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-izmena-podataka',
  templateUrl: './izmena-podataka.component.html',
  styleUrls: ['./izmena-podataka.component.css']
})
export class IzmenaPodatakaComponent implements OnInit {
  
  korisnik: any = null;
  loginForm: any = null;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  constructor(private fb: FormBuilder, private korisniciServis: KorisniciService, private ruter: Router, 
      private snackBar: MatSnackBar, ) {
    this.loginForm = this.fb.group({
      ime: ['', Validators.required],
      prezime: ['', Validators.required]
    });
    this.korisniciServis.getInfo().subscribe((data:any) => {
      this.korisnik = data;
      this.loginForm = this.fb.group({
        ime: [data.ime, Validators.required],
        prezime: [data.prezime, Validators.required]
      });
    });
   }

  ngOnInit(): void {
  }

  sacuvaj() {
    this.korisnik.ime = this.loginForm.getRawValue().ime;
    this.korisnik.prezime = this.loginForm.getRawValue().prezime;
    this.korisniciServis.setInfo(this.korisnik)
      .subscribe((data:any) => {
        this.ispisPoruke("Uspešno ste izmenili podatke :)");
        if (data.korisnikoIme == "dumit") this.ruter.navigate(['/ProfilAdmin']);
        else this.ruter.navigate(['/Profil']);
      });
  }

  public postojiGreska = (controlName: string, errorName: string) => {
    return this.loginForm.controls[controlName].hasError(errorName);
  }
  
  ispisPoruke(msg: string) {
    this.snackBar.open(msg, "x", {
      duration: 3000,
      verticalPosition: this.verticalPosition,
      panelClass: "back-green"
    });
  }
}
