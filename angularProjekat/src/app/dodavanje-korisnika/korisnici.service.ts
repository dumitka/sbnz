import { Injectable } from '@angular/core';
import { Main } from '../../main';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class KorisniciService {

  constructor(private http: HttpClient) { }
  
  readonly URL : string = Main.PATH + "korisnici/";

  dodajKorisnika(korisnik: any){
    return this.http.post(this.URL + "dodaj", korisnik);
  }
}
