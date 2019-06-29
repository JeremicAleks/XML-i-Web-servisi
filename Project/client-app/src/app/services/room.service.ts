import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(private http: HttpClient) { }

  getTypes() {
    //Content-Type multipart/form-data
    return this.http.get<any>(environment.centralApiUrl + '/api/room/types').pipe(map(data => {
      return data;
    }
    ))
  }
  getCategories() {
    //Content-Type multipart/form-data
    return this.http.get<any>(environment.centralApiUrl + '/api/room/categories').pipe(map(data => {
      return data;
    }
    ))
  }
  getAdditionalServices() {
    //Content-Type multipart/form-data
    return this.http.get<any>(environment.centralApiUrl + '/api/room/additionalServices').pipe(map(data => {
      return data;
    }
    ))
  }
}
