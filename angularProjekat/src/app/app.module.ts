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


@NgModule({
  declarations: [
    AppComponent,
    LogovanjeComponent,
    ProfilComponent
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
        canActivate: [],
        component: ProfilComponent,
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
