import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment'
import { Reservation } from '../models/reservation';
import { Room } from '../models/room';
import { SendMessageDto } from '../models/send-message-dto';
import { AllowReservation } from '../models/allow-reservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient, private router: Router) { }


  addReservation(res:Reservation,room:number) {
    var  id = String(room);
    return this.http.post<any>(environment.agentApiUrl + '/api/reserveRoom/',
    res,{ params: {idRoom: id}}).pipe(map(data => {
      return data;
    }
    ))
  }

  
  getMessages() {
    return this.http.get<any>(environment.agentApiUrl + '/api/message/all').pipe(map(data => {
      return data;
    }
    ))
  }

  getReservations() {
    return this.http.get<any>(environment.agentApiUrl + '/api/reservation/all').pipe(map(data => {
      return data;
    }
    ))
  }

  
  
  sendMessage(message:SendMessageDto) {
    return this.http.post<any>(environment.agentApiUrl + '/api/message',message).pipe(map(data => {
      return data;
    }
    ))
  }

  allowReservation(allowRes:AllowReservation) {
    return this.http.post<any>(environment.agentApiUrl + '/api/reservation',allowRes).pipe(map(data => {
      return data;
    }
    ))
  }

}
