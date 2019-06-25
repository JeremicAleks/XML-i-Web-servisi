import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { AccommodationDTO } from '../models/accommodation-dto';

@Injectable({
  providedIn: 'root'
})
export class AccommodationService {

  constructor(private http: HttpClient, private router: Router) { }

  createAccommodationType(accommodationType: AccommodationDTO) {
    console.log(accommodationType.description);
    return this.http.post<any>(environment.centralApiUrl + '/api/accommodation/newType', accommodationType)
    .pipe(map(retVal => {
      return retVal;
    }
    ));
  }

  deleteAccommodationType(id: number) {
    return this.http.delete<any>(environment.centralApiUrl + '/api/accommodation/deleteType' + id )
    .pipe(map(retVal => {
      return retVal;
    }
    ));
  }

  getAccommodationTypes() {
    return this.http.get<any>(environment.centralApiUrl + '/api/accommodation/types')
    .pipe(map(accommodationTypes => {
      return accommodationTypes;
    }
    ));
  }
}
