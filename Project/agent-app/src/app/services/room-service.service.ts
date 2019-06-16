import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment'
import { TypeEnum, Room } from '../models/room';

@Injectable({
  providedIn: 'root'
})
export class RoomServiceService {

  constructor(private http: HttpClient, private router: Router) { }


  addRoom(room:Room) {
    //Content-Type multipart/form-data
    return this.http.post<any>(environment.agentApiUrl + '/api/addRoom',
    room).pipe(map(user => {
    }
    ))
  }

  
  addFiles(room:FormData) {
    // 
    return this.http.post<FormData>(environment.agentApiUrl + '/api/addFile',
    room,).pipe(map(user => {
    }
    ))
  }

}
