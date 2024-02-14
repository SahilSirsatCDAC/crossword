import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError  } from 'rxjs';
import { catchError } from 'rxjs/operators';
const baseUrl = environment.apiUrl;
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  constructor(private http: HttpClient) { }
  getUser(userId : number): Observable<any> {
    return this.http.get<any>(baseUrl + "/user/id/"+userId).pipe(
      catchError((error) => {
        console.error('Error fetching user:', error);
        return throwError(() => new Error('test'));
      })
    );
  }

  loginUser(postData: any): Observable<any> {
    return this.http.post<any>(baseUrl + "/auth/signin", postData).pipe(
      catchError((error) => {
        console.error('Error fetching posts:', error);
        return throwError(() => new Error('test'));
      })
    );
  }

  registerUser(postData: any): Observable<any> {
    return this.http.post<any>(baseUrl + "/auth/signup", postData).pipe(
      catchError((error) => {
        console.error('Error fetching posts:', error);
        return throwError(() => new Error('test'));
      })
    );
  }
}
