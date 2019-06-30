import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { ClientSendMessageDTO } from '../models/client-send-message-dto';
import {Review} from '../models/review';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) { }

  getAllReservations() {
    return this.http.get<any>(environment.centralApiUrl + '/api/reservation/all')
      .pipe(map(allReservations => {
        return allReservations;
      }
      ));
  }

  cancelReservation(idReservation:number) {
    return this.http.post<any>(environment.centralApiUrl + '/api/reservation/cancel/' + idReservation, '')
  }

   getMessagesForReservation(idReservation: number) {
    return this.http.get<any>(environment.centralApiUrl + '/api/reservation/getMessageForUser/' + idReservation)
      .pipe(map(data => {
        return data;
      }
      ));
  }

  sendMessage(clientSendMessageDTO: ClientSendMessageDTO) {
    return this.http.post<any>(environment.centralApiUrl + '/api/reservation/sendMessageClient', clientSendMessageDTO)
      .pipe(map(data => {
        return data;
      }
      ));
  }

  sendReview(review: Review) {
    return this.http.post<any>(environment.centralApiUrl + '/api/comment/addComment', review)
      .pipe(map(data => {
          return data;
        }
      ));
  }
}
