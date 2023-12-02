import { Injectable, EventEmitter, Output } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { environment } from './../../../environments/environment';
import { SecurityService } from './security.service';
import { UserServer } from '../Model/userServer';
import { MyCookieService } from '../Utils/MyCookieService';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly API = `${environment.API}`;

  @Output() userEventEmitter = new EventEmitter<UserServer>();
  @Output() userRefreshEmitter = new EventEmitter<boolean>();
  constructor(private httpClient: HttpClient,
    private cookieService: MyCookieService,
    private sec: SecurityService) {}

  emittUser() {
    this.userEventEmitter.emit(null);
  }
  updateUser(p: UserServer) {
    this.userEventEmitter.emit(p);
  }
  refreshUserTable() {
    this.userRefreshEmitter.emit(true);
  }
  addUser(prod: UserServer): Observable<string> {
    return this.httpClient
      .post<string>(this.API.concat('/users/s'), prod, {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequest()
      })
      .pipe();
  }
  updUser(prod: UserServer): Observable<string> {
    return this.httpClient
      .put<string>(this.API.concat('/users/u'), prod, {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequest()
      })
      .pipe();
  }
  delUser(idProd): Observable<string> {
    return this.httpClient
      .delete<string>(`${this.API}/users/d/${idProd}`, {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequest()
      })
      .pipe();
  }
  getUsers(pag: string, lim: string): Observable<UserServer[]> {
    return this.httpClient
      .get<UserServer[]>(this.API.concat('/users/lstuser'), {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequestWithOptions(pag, lim)
      })
      .pipe();
  }
  searchUsers(pag: string, lim: string, name: string): Observable<UserServer[]> {
    return this.httpClient
      .get<UserServer[]>(this.API.concat('/users/lstuserearch'), {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequestSearchWithOptions(pag, lim, name)
      })
      .pipe();
  }
}
