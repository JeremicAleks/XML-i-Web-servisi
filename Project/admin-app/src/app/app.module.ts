import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
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
import { AdminCodeBookAccommodationTypeComponent } from './views/admin-code-book-accommodation-type/admin-code-book-accommodation-type.component';
import { AdminCodeBookAccommodationCategoryComponent } from './views/admin-code-book-accommodation-category/admin-code-book-accommodation-category.component';
import { AdminCodeBookAdditionalServiceComponent } from './views/admin-code-book-additional-service/admin-code-book-additional-service.component';
import { AdminCommentConfigComponent } from './views/admin-comment-config/admin-comment-config.component';

@NgModule({
  declarations: [
    AppComponent,
    MainNavibarComponent,
    AdminRoleConfigurationComponent,
    AdminUserConfigurationComponent,
    AdminCodeBookAccommodationTypeComponent,
    AdminCodeBookAccommodationCategoryComponent,
    AdminCodeBookAdditionalServiceComponent,
    AdminCommentConfigComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [ DatePipe,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ],
  bootstrap: [ AppComponent ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class AppModule { }
