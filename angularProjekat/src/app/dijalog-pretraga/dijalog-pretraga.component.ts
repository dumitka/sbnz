import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-dijalog-pretraga',
  templateUrl: './dijalog-pretraga.component.html',
  styleUrls: ['./dijalog-pretraga.component.css']
})
export class DijalogPretragaComponent implements OnInit {
  public pretraga: FormGroup;

  constructor(public dialogRef: MatDialogRef<DijalogPretragaComponent>, private formBuilder: FormBuilder,) {
    this.pretraga = this.formBuilder.group({
      tekst: ['', [Validators.required, ]]
    });
  }

  public requiredError = (errorName: string) =>{
    return this.pretraga.controls['tekst'].hasError(errorName);
  }
  
  pretrazi(): void {
    this.dialogRef.close(this.pretraga.value.tekst);
  }

  ngOnInit(): void {
  }
}
