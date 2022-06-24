import { Injectable } from '@angular/core';
import { Main } from '../../main';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class KnjigeService {

  readonly URL : string = Main.PATH + "knjige/";

  constructor(private http: HttpClient) { }

  preporuka(korisnickoIme: String, isbn: String)  { return this.http.get(this.URL + "preporuka/" + korisnickoIme + "/" + isbn); }
}
