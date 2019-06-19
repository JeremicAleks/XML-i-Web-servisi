import { BrowserModule } from '@angular/platform-browser';
import { NgModule , CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainNavbarComponent } from './main-navbar/main-navbar.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ReservationsComponent } from './reservations/reservations.component';
import { NgbModal, NgbTabset, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {FileSelectDirective} from "ng2-file-upload";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MatMomentDateModule } from '@angular/material-moment-adapter';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';

@NgModule({
  declarations: [
    AppComponent,
    MainNavbarComponent,
    ReservationsComponent,
    FileSelectDirective
  ],
  imports: [
    BrowserModule,
    MatDatepickerModule,        // <----- import(must)
    MatNativeDateModule,        // <----- import for date formating(optional)
    MatMomentDateModule, 
    BrowserAnimationsModule,        // <----- import for date formating adapted to more locales(optional)
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
    
  ],
  exports: [
    MatDatepickerModule, 
    MatNativeDateModule 
],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA,
  ],
})
export class AppModule { }
