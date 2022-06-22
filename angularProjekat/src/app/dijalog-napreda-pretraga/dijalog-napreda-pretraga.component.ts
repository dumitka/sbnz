import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-dijalog-napreda-pretraga',
  templateUrl: './dijalog-napreda-pretraga.component.html',
  styleUrls: ['./dijalog-napreda-pretraga.component.css']
})
export class DijalogNapredaPretragaComponent implements OnInit {
  
  public pretraga: FormGroup;

  constructor(public dialogRef: MatDialogRef<DijalogNapredaPretragaComponent>, private formBuilder: FormBuilder,) {
    this.pretraga = this.formBuilder.group({
      tekst: ['', [Validators.required, ]],
      broj: [5, [Validators.required, Validators.min(1)]],
    });
  }

  public requiredError = (elem: string, errorName: string) =>{
    return this.pretraga.controls[elem].hasError(errorName);
  }
  
  public minError = (errorName: string) =>{
    return this.pretraga.controls['broj'].hasError(errorName);
  }
  
  pretrazi(): void {
    this.dialogRef.close({'tekst': this.pretraga.value.tekst, 'broj': this.pretraga.value.broj});
  }

  ngOnInit(): void {
  }

}
