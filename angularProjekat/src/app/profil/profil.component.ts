import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {
  ime:string;
  prezime:string;

  constructor() {
    this.ime = "Mara";
    this.prezime = "Maric";
   }

  ngOnInit(): void {
  }

}
