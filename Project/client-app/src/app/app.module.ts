import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainNavibarComponent } from './views/main-navibar/main-navibar.component';
import { AdminRoleConfigurationComponent } from './views/admin-role-configuration/admin-role-configuration.component';

@NgModule({
  declarations: [
    AppComponent,
    MainNavibarComponent,
    AdminRoleConfigurationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
