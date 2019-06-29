import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { PrivilegeEnum } from '../models/privilege-enum';
import { AgentDTO } from '../models/agent-dto';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private router: Router) { }

  getAllUsers() {
    return this.http.get<any>(environment.centralApiUrl + '/api/user/getUsers')
    .pipe(map(data => {
      return data;
    }
    ));
  }

  getAllRoles() {
    return this.http.get<any>(environment.centralApiUrl + '/api/role/getAllRoles')
    .pipe(map(data => {
      return data;
    }
    ));
  }

  createNewAgent(newAgent: AgentDTO) {
    alert(newAgent.userToPromote);
    return this.http.post<any>(environment.centralApiUrl + '/api/admin/agent/add', newAgent )
    .pipe(map(data => {
      return data;
    }
    ));
  }

  activateUser(username: string) {
    return this.http.put<any>(environment.centralApiUrl + '/api/admin/user/activate', username )
    .pipe(map(data => {
      return data;
    }
    ));
  }

  blockUser(username: String) {
    return this.http.put<any>(environment.centralApiUrl + '/api/admin/user/block', username )
    .pipe(map(data => {
      return data;
    }
    ));
  }

  deleteUser(username: string) {
    return this.http.put<any>(environment.centralApiUrl + '/api/admin/user/delete', username )
    .pipe(map(data => {
      return data;
    }
    ));
  }

  updateUser(username: string, role: string) {
    return this.http.post<any>(environment.centralApiUrl + '/api/role/changeRoleForUser',
    { username, role })
    .pipe(map(data => {
      return data;
    }
    ));
  }

  deleteRole(role: string) {
    return this.http.post<any>(environment.centralApiUrl + '/api/role/deleteRole', { role } )
    .pipe(map(data => {
      return data;
    }
    ));
  }

  createRole(role: string) {
    return this.http.post<any>(environment.centralApiUrl + '/api/role/addRole', { role } )
    .pipe(map(data => {
      return data;
    }
    ));
  }

  updateRole(privileges: Array<PrivilegeEnum>, role: string) {
    return this.http.post<any>(environment.centralApiUrl + '/api/role/updateRole', { privileges, role } )
    .pipe(map(data => {
      return data;
    }
    ));
  }

}
