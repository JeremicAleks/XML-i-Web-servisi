import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { UserLoginDTO } from '../models/user-login-dto';


@Injectable({ providedIn: 'root' })
export class SecAdminGuard implements CanActivate {
  private sessionUser:UserLoginDTO

    constructor(private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        this.sessionUser = JSON.parse(localStorage.getItem('sessionUser'));
        if (this.sessionUser !=null && this.sessionUser.role=="SECADMIN") {
            return true;
        }

        // not logged in so redirect to login page with the return url
        this.router.navigate(['/'], { queryParams: { returnUrl: state.url }});
        return false;
    }
}