import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NgbModule, NgbPaginationModule, NgbAlertModule } from '@ng-bootstrap/ng-bootstrap'; // bootstrap
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; // material design

import { AdminRoleConfigurationComponent } from './views/admin-role-configuration/admin-role-configuration.component';
import { AdminUserConfigurationComponent } from './views/admin-user-configuration/admin-user-configuration.component';
import { SecAdminGuard } from './utils/sec-admin-guard';
import {DestinationsComponent} from "./views/destinations/destinations.component";
import {MainHomepageComponent} from "./views/main-homepage/main-homepage.component";
import {ReservationComponent} from "./views/reservation/reservation.component";
import {ChangePasswordComponent} from "./views/change-password/change-password.component";

const routes: Routes = [

  {
    path: 'admin/config/user',
    component: AdminUserConfigurationComponent, canActivate: [SecAdminGuard]
  },

  {
    path: 'admin/config/role',
    component: AdminRoleConfigurationComponent, canActivate: [SecAdminGuard]
  },
  {
    path: 'searchResult/:destination/:checkIn/:checkOut/:numOfPeople',
    component: DestinationsComponent
  },
  {
    path: '',
    component: MainHomepageComponent
  },
  {
    path: 'reservation',
    component: ReservationComponent
  },
  {
    path: 'changePassword',
    component: ChangePasswordComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    NgbModule,
    NgbPaginationModule,
    NgbAlertModule,
    BrowserAnimationsModule,
  ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
