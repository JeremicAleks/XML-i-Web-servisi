import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {environment} from '../../environments/environment';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(private http: HttpClient, private router: Router) { }

  getAllRooms() {
    return this.http.get<any>(environment.centralApiUrl + '/api/room/all')
      .pipe(map(allRooms => {
          return allRooms;
        }
      ));
  }
}
