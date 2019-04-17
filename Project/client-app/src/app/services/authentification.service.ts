import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment'

@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {

  constructor(private http: HttpClient, private router: Router) { }

  signIn(username: string, password: string) {
    return this.http.post<any>(environment.authentificationApiUrl + '/api/token', {
      username, password
    }).pipe(map(user => {
      if (user && user.token) {
        localStorage.setItem('sessionUser', JSON.stringify(user));
      }
      return user;
    }
    ));
  }

  register(name: string, lastname: string, email: string, username: string, password: string, rePassword : string) {
    return this.http.post<any>(environment.authentificationApiUrl + '/api/register', {
      username, password, lastname, email, rePassword, name
    }).pipe(map(message => {
      return message;
    }
    ));
  }

  logout() {
    localStorage.removeItem('sessionUser');
  }

  checkSessionUser() {
    let sessionUser = JSON.parse(localStorage.getItem('sessionUser'));
    if (sessionUser && sessionUser.token) {
      return true;
    }

    return false;
  }

  getSessionUser() {
    let sessionUser = JSON.parse(localStorage.getItem('sessionUser'));
    if (sessionUser) {
      return sessionUser;
    }

    return null;
  }

}
