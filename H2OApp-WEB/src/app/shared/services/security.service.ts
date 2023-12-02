import { Injectable, EventEmitter, Output } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs';
import { of } from 'rxjs/observable/of';

import { environment } from './../../../environments/environment';
import { User } from './../Model/user';
@Injectable({
  providedIn: 'root'
})
export class SecurityService {
  private readonly API = `${environment.API}`;
  private storageAlert: Subject<any> = new Subject();
  private name: string;
  isOpen = false;
  @Output() change: EventEmitter<string> = new EventEmitter();
  constructor(private httpClient: HttpClient) {}

  testForm(json) {
    this.httpClient
      .post('https://httpbin.org/post', json)
      .map(res => res)
      .subscribe(
        dados => {
          console.log(dados);
        },
        (error: any) => alert('erro')
      );
  }
  getToken(userName: string, password: string): Observable<User> {
    const data = {
      username: userName,
      password: password
    };
    return this.httpClient.get<User>(this.API.concat('/login'), {
      params: data
    });
  }
  toggle() {
    this.name = sessionStorage.getItem('user_Name');
    this.change.emit(this.name);
  }
  getNameLogged() {
    if (this.isLogged()) {
        return sessionStorage.getItem('user_Name');
    } else {
       return '';
    }
  }
  getValue(): Observable<any> {
    this.storageAlert.next(sessionStorage.getItem('user_Name'));
    return this.storageAlert.asObservable();
  }
  logOut() {
    const keysToRemove = ['a_t', 'user_Name', 'r_t', 'log', 't_n'];
    keysToRemove.forEach(k =>
      sessionStorage.removeItem(k));
      this.toggle();
      return true;
  }
  isLogged() {
   if (undefined !== sessionStorage.getItem('log') && 's' === sessionStorage.getItem('log') ) {
     return true;
   } else {
     return false;
   }
  }
  getAcToken() {
    if (undefined !== sessionStorage.getItem('log') && 's' === sessionStorage.getItem('log') ) {
      return sessionStorage.getItem('a_t');
    } else {
      return null;
    }
  }
  getParamsToRequest(): HttpParams {
    const pall = new HttpParams()
    .set('access_token', this.getAcToken());
    return pall;
  }
  getParamsToRequestWithOptions(pag: string, lim: string): HttpParams {
    const pall = new HttpParams()
    .set('access_token', this.getAcToken())
    .set('l', lim)
    .set('p', pag);
    return pall;
  }
  getParamsToRequestSearchWithOptions(pag: string, lim: string, name: string): HttpParams {
    const pall = new HttpParams()
    .set('access_token', this.getAcToken())
    .set('l', lim)
    .set('p', pag)
    .set('n', name);
    return pall;
  }
  getIdCompany() {
    if (undefined !== sessionStorage.getItem('t_n')) {
      return sessionStorage.getItem('t_n');
    } else {
      return null;
    }
  }
  saveToken(u: User, b: string) {
    try {
      sessionStorage.setItem('a_t', u.at);
      sessionStorage.setItem('user_Name', u.na);
      sessionStorage.setItem('r_t', u.rt);
      sessionStorage.setItem('t_n', u.tn);
      sessionStorage.setItem('log', b );
    } catch (e) {
      this.log('Error: ' + e);
    }
  }
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
  private log(message: string) {
    console.log('SecurityService: ' + message);
  }
}
