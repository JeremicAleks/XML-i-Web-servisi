import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NgbModule, NgbPaginationModule, NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; //material design
import { ReservationsComponent } from './reservations/reservations.component';

const routes: Routes = [
  {
    path: '',
    component:  ReservationsComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
    BrowserAnimationsModule],

  exports: [RouterModule]
})
export class AppRoutingModule { }
