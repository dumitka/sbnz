import { Injectable } from '@angular/core';
import { Main } from '../../main';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class KnjigeService {

  readonly URL : string = Main.PATH + "knjige/";

  constructor(private http: HttpClient) { }

  pretraga(parametar: string)  { return this.http.get(this.URL + "pretraga/" + parametar); }
  staraPretraga()  { return this.http.get(this.URL + "stariTemplejt"); }
  generisiTemplejt(parametar: any) { return this.http.get(this.URL + "generisiTemplejt/" + parametar.tekst + "/" + parametar.broj); }
}
