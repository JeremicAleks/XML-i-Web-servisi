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

  // ADDITIONAL SERVICE
  updateAdditionalService(additionalService: AccommodationDTO) {
    console.log(additionalService.description);
    return this.http.put<any>(environment.centralApiUrl + '/api/additionalService/updateService', additionalService )
    .pipe(map(retVal => {
      return retVal;
    }
    ));
  }

  createAdditionalService(additionalService: AccommodationDTO) {
    console.log(additionalService.description);
    return this.http.post<any>(environment.centralApiUrl + '/api/additionalService/newService', additionalService )
    .pipe(map(retVal => {
      return retVal;
    }
    ));
  }

  deleteAdditionalService(additionalService: AccommodationDTO) {
    return this.http.post<any>(environment.centralApiUrl + '/api/additionalService/deleteService', additionalService )
    .pipe(map(retVal => {
      return retVal;
    }
    ));
  }

  getAdditionalServices() {
    return this.http.get<any>(environment.centralApiUrl + '/api/additionalService')
    .pipe(map(accommodationCategories => {
      return accommodationCategories;
    }
    ));
  }


  // ACCOMMODATION CATEGORY
  updateAccommondationCategory(accommodationCategory: AccommodationDTO) {
    console.log(accommodationCategory.description);
    return this.http.put<any>(environment.centralApiUrl + '/api/accommodation/updateCategory', accommodationCategory )
    .pipe(map(retVal => {
      return retVal;
    }
    ));
  }

  createAccommodationCategory(accommodationCategory: AccommodationDTO) {
    console.log(accommodationCategory.description);
    return this.http.post<any>(environment.centralApiUrl + '/api/accommodation/newCategory', accommodationCategory )
    .pipe(map(retVal => {
      return retVal;
    }
    ));
  }

  deleteAccommodationCategory(accommodationCategory: AccommodationDTO) {
    return this.http.post<any>(environment.centralApiUrl + '/api/accommodation/deleteCategory', accommodationCategory )
    .pipe(map(retVal => {
      return retVal;
    }
    ));
  }

  getAccommodationCategories() {
    return this.http.get<any>(environment.centralApiUrl + '/api/accommodation/categories')
    .pipe(map(accommodationCategories => {
      return accommodationCategories;
    }
    ));
  }

  // ACCOMMODATION TYPE
  updateAccommondationType(accommodationType: AccommodationDTO) {
    console.log(accommodationType.description);
    return this.http.put<any>(environment.centralApiUrl + '/api/accommodation/updateType', accommodationType )
    .pipe(map(retVal => {
      return retVal;
    }
    ));
  }

  createAccommodationType(accommodationType: AccommodationDTO) {
    console.log(accommodationType.description);
    return this.http.post<any>(environment.centralApiUrl + '/api/accommodation/newType', accommodationType )
    .pipe(map(retVal => {
      return retVal;
    }
    ));
  }

  deleteAccommodationType(accommodationType: AccommodationDTO) {
    return this.http.post<any>(environment.centralApiUrl + '/api/accommodation/deleteType', accommodationType )
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
