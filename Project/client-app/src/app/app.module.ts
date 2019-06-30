import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainNavibarComponent } from './views/main-navibar/main-navibar.component';
import { AdminRoleConfigurationComponent } from './views/admin-role-configuration/admin-role-configuration.component';
import { JwtInterceptor } from './utils/authInterceptors/jwt-interceptor';
import { ErrorInterceptor } from './utils/authInterceptors/error-interceptor';
import { DatePipe } from '@angular/common';
import { AdminUserConfigurationComponent } from './views/admin-user-configuration/admin-user-configuration.component';
import { MainHomepageComponent } from './views/main-homepage/main-homepage.component';
import {NgbDatepickerModule, NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { DestinationsComponent } from './views/destinations/destinations.component';
import { ReservationComponent } from './views/reservation/reservation.component';
import { ChangePasswordComponent } from './views/change-password/change-password.component';
import {NavbarComponent} from "./views/navbar/navbar.component";
import { UserProfileComponent } from './views/user-profile/user-profile.component';
import { RoomListComponent } from './views/room-list/room-list.component';
import { ReservationConversationComponent } from './views/reservation-conversation/reservation-conversation.component';

@NgModule({
  declarations: [
    AppComponent,
    MainNavibarComponent,
    AdminRoleConfigurationComponent,
    AdminUserConfigurationComponent,
    MainHomepageComponent,
    DestinationsComponent,
    ReservationComponent,
    ChangePasswordComponent,
    NavbarComponent,
    UserProfileComponent,
    RoomListComponent,
    ReservationConversationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbDatepickerModule,
    NgbModule.forRoot()
  ],
  providers: [ DatePipe,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
