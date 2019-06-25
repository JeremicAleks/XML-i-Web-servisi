import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AccommodationService {

  constructor(private http: HttpClient, private router: Router) { }

  getAccommodationTypes() {
    return this.http.get<any>(environment.centralApiUrl + '/api/accommodation/types')
    .pipe(map(accommodationTypes => {
      return accommodationTypes;
    }
    ));
  }
}
