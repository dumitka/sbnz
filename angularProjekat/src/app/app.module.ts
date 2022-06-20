import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AngularMaterialModule } from './angular_material.module';
import { AppComponent } from './app.component';
import { LogovanjeComponent } from './logovanje/logovanje.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProfilComponent } from './profil/profil.component';
import { TokenInterceptor } from './logovanje/TokenInterceptor';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginService } from './logovanje/login.service';
import { ProfilAdminComponent } from './profil-admin/profil-admin.component';
import { Admin } from './uloge/Admin';
import { Korisnik } from './uloge/Korisnik';
import { IzmenaPodatakaComponent } from './izmena-podataka/izmena-podataka.component';

@NgModule({
  declarations: [
    AppComponent,
    LogovanjeComponent,
    ProfilComponent,
    ProfilAdminComponent,
    IzmenaPodatakaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    AngularMaterialModule,

    
    RouterModule.forRoot([
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: 'login', component: LogovanjeComponent },
      {
        path: 'Profil',
        canActivate: [Korisnik],
        component: ProfilComponent,
      },
      {
        path: 'ProfilAdmin',
        canActivate: [Admin],
        component: ProfilAdminComponent,
      },
      {
        path: 'IzmenaPodataka',
        canActivate: [],
        component: IzmenaPodatakaComponent,
      },
    ]),

    BrowserAnimationsModule,
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true,
    },
    LoginService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
