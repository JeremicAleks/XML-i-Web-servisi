import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient, private router: Router) { }

  getAllComments() {
    return this.http.get<any>(environment.centralApiUrl + '/api/comment/getComments')
    .pipe(map(allComments => {
      return allComments;
    }
    ));
  }

  approveComment(id: number) {
    return this.http.get<any>(environment.centralApiUrl + '/api/comment/approveComment/' + id)
    .pipe(map(allComments => {
      return allComments;
    }
    ));
  }
}
