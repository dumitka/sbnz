import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { MatDialog } from '@angular/material/dialog';
import { DijalogNapredaPretragaComponent } from '../dijalog-napreda-pretraga/dijalog-napreda-pretraga.component';

@Component({
  selector: 'app-dijalog-odabir',
  templateUrl: './dijalog-odabir.component.html',
  styleUrls: ['./dijalog-odabir.component.css']
})
export class DijalogOdabirComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DijalogOdabirComponent>, public dialog: MatDialog,) {
  }
  
  pretrazi(nastavak: boolean): void {
    if (nastavak) {
      const dialogRef = this.dialog.open(DijalogNapredaPretragaComponent, {width: '500px',});

      dialogRef.afterClosed().subscribe(result => {
        this.dialogRef.close(result);
      });

      return;
    }
    this.dialogRef.close(null);
  }

  ngOnInit(): void {
  }

}
