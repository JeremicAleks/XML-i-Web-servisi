import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment'
import { PrivilegeEnum } from '../models/privilege-enum';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private router: Router) { }

  getAllUsers() {
    return this.http.get<any>(environment.centralApiUrl + '/api/user/getUsers')
    .pipe(map(allUsers => {
      return allUsers;
    }
    ));
  }

  getAllRoles() {
    return this.http.get<any>(environment.centralApiUrl + '/api/role/getAllRoles')
    .pipe(map(allRoles => {
      return allRoles;
    }
    ));
  }

  updateUser(username: string, role: string) {
    return this.http.post<any>(environment.centralApiUrl + '/api/role/changeRoleForUser',
    { username, role })
    .pipe(map(retVal => {
      return retVal;
    }
    ));
  }

  deleteRole(role: string) {
    return this.http.post<any>(environment.centralApiUrl + '/api/role/deleteRole', { role } )
    .pipe(map(retVal => {
      return retVal;
    }
    ));
  }

  createRole(role: string) {
    return this.http.post<any>(environment.centralApiUrl + '/api/role/addRole', { role } )
    .pipe(map(retVal => {
      return retVal;
    }
    ));
  }

  updateRole(privileges: Array<PrivilegeEnum>, role: string) {
    return this.http.post<any>(environment.centralApiUrl + '/api/role/updateRole', { privileges, role } )
    .pipe(map(retVal => {
      return retVal;
    }
    ));
  }

}
