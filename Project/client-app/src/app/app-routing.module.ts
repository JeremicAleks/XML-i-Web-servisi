import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap'; //bootstrap
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; //material design

const routes: Routes = [];

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
