import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment'
import { Reservation } from '../models/reservation';
import { Room } from '../models/room';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient, private router: Router) { }


  addReservation(res:Reservation,room:Room) {
    var  id = String(room.id);
    return this.http.post<any>(environment.agentApiUrl + '/api/reserveRoom/',
    room,{ params: {idRoom: id}}).pipe(map(data => {
      return data;
    }
    ))
  }

}
