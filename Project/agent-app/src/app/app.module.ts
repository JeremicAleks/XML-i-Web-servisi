import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainNavbarComponent } from './main-navbar/main-navbar.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ReservationsComponent } from './reservations/reservations.component';
import { NgbModal, NgbTabset, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {FileSelectDirective} from "ng2-file-upload";

@NgModule({
  declarations: [
    AppComponent,
    MainNavbarComponent,
    ReservationsComponent,
    FileSelectDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
