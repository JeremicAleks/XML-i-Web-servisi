import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment';
import {map} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';

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
    return this.http.post<any>(environment.centralApiUrl + '/api/reservation/cancel/' +idReservation,'')
      .pipe(map(data => {
          return data;
        }
      ));
  }
}
