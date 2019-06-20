import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NgbModule, NgbPaginationModule, NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; //material design
import { ReservationsComponent } from './reservations/reservations.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {
    path: 'panel',
    component:  ReservationsComponent
  },
  {
    path: '',
    component:  HomeComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
    BrowserAnimationsModule],

  exports: [RouterModule]
})
export class AppRoutingModule { }
