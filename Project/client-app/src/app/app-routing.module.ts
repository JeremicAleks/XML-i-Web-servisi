import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NgbModule, NgbPaginationModule, NgbAlertModule } from '@ng-bootstrap/ng-bootstrap'; // bootstrap
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; // material design

import { AdminRoleConfigurationComponent } from './views/admin-role-configuration/admin-role-configuration.component';
import { AdminUserConfigurationComponent } from './views/admin-user-configuration/admin-user-configuration.component';
import { SecAdminGuard } from './utils/sec-admin-guard';

const routes: Routes = [

  {
    path: 'admin/config/user',
    component: AdminUserConfigurationComponent, canActivate: [SecAdminGuard]
  },

  {
    path: 'admin/config/role',
    component: AdminRoleConfigurationComponent, canActivate: [SecAdminGuard]
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
