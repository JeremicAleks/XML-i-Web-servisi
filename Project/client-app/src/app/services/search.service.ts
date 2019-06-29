import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {map} from 'rxjs/operators';
import { SearchParams } from '../models/search-params';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }

  getSearchResults(searchParams: SearchParams) {
    return this.http.post<any>(environment.centralApiUrl + '/api/search', searchParams)
      .pipe(map(data => {
          return data;
        }
      ));
  }
}
