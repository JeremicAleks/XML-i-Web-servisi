import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap'; //bootstrap
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; //material design

import { AdminRoleConfigurationComponent } from './views/admin-role-configuration/admin-role-configuration.component';

const routes: Routes = [

  {
    path: 'admin/config',
    component: AdminRoleConfigurationComponent
  }

];

@NgModule({
  imports: 
  [ 
    RouterModule.forRoot(routes),
    NgbModule,
    BrowserAnimationsModule,
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
