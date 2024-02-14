import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
const baseUrl = environment.apiUrl;
@Injectable({
  providedIn: 'root'
})
export class CrosswordService {

  constructor(private http: HttpClient) { }
  getCrosswords(): Observable<any[]> {
    return this.http.get<any>(baseUrl + "/crossword/0/2").pipe(
      catchError((error) => {
        console.error('Error fetching posts:', error);
        return throwError(() => new Error('test'));
      })
    );
  }
  getWords(crosswordId:number): Observable<any> {
    return this.http.get<any>(baseUrl + "/word/" + crosswordId).pipe(
      catchError((error) => {
        console.error('Error fetching posts:', error);
        return throwError(() => new Error('test'));
      })
    );
  }
  saveProgress(wordIds:number[] ,crosswordId:number){
    var postData: any = {};
    postData['wordIds']=wordIds;
    postData['crosswordId']=crosswordId;
    return this.http.post<any>(baseUrl + "/user/progress/save", postData).pipe(
      catchError((error) => {
        console.error('Error fetching posts:', error);
        return throwError(() => new Error('test'));
      })
    );
  }
}
