import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddCertificateComponent } from './add-certificate/add-certificate.component';

const routes: Routes = [
  {path:'addCertificate', component:AddCertificateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
