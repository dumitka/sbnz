import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { HttpClient } from '@angular/common/http';
import { LoginService } from './login.service';

@Component({
  selector: 'app-logovanje',
  templateUrl: './logovanje.component.html',
  styleUrls: ['./logovanje.component.css']
})
export class LogovanjeComponent implements OnInit {
  loginForm: any = null;
  RESPONSE_OK: number = 0;
  RESPONSE_ERROR: number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  constructor(private fb: FormBuilder, private router: Router, private snackBar: MatSnackBar, 
    private loginService: LoginService, private http: HttpClient) { }

  ngOnInit(): void {
    let username = history.state.data === undefined ? '' : history.state.data.username;
    this.loginForm = this.fb.group({
      username: [username, Validators.required],
      password: ['', Validators.required]
    });
    if (username !== '') {
      this.loginForm.controls['username'].disable();
    }
  }

  public hasError = (controlName: string, errorName: string) => {
    return this.loginForm.controls[controlName].hasError(errorName);
  }

  login() {
    this.loginService.login(this.loginForm.getRawValue())
      .subscribe(
        data => {
          this.openSnackBar("Uspešno ste ulogovani. Dobrodošli! :) " + this.loginService.getTokenData()?.role, this.RESPONSE_OK);
          if (this.loginService.getTokenData()?.role === "ADMIN") {
            this.router.navigate(['/ProfilAdmin']);
          } else if (this.loginService.getTokenData()?.role === "KORISNIK") {
            this.router.navigate(['/Profil']);
          }
        },
        error => {
          if (error.status === 404) {
            this.openSnackBar("Pogrešno korisničko ime ili lozinka :)", this.RESPONSE_OK);
          }
        });
  }

  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }
}
