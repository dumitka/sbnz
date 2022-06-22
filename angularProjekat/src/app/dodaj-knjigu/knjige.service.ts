import { Injectable } from '@angular/core';
import { Main } from '../../main';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class KnjigeService {

  constructor(private http: HttpClient) { }
  
  readonly URL : string = Main.PATH + "knjige/";

  dodajKnjigu(knjiga: any){
    return this.http.post(this.URL + "dodaj", knjiga);
  }
}
