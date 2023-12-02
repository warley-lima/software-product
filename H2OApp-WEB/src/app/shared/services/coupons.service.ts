import { Injectable, EventEmitter, Output } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { environment } from './../../../environments/environment';
import { SecurityService } from './security.service';
import { Coupon } from '../Model/coupon';
import { MyCookieService } from '../Utils/MyCookieService';

@Injectable({
  providedIn: 'root'
})
export class CouponsService {

  private readonly API = `${environment.API}`;

  @Output() couponEventEmitter = new EventEmitter<Coupon>();
  @Output() couponRefreshEmitter = new EventEmitter<boolean>();
  constructor(
    private httpClient: HttpClient,
    private cookieService: MyCookieService,
    private sec: SecurityService) {}

  emittNew() {
    this.couponEventEmitter.emit(null);
  }
  update(p: Coupon) {
    this.couponEventEmitter.emit(p);
  }
  refreshTable() {
    this.couponRefreshEmitter.emit(true);
  }
  add(prod: Coupon): Observable<string> {
    return this.httpClient
      .post<string>(this.API.concat('/cupons/s'), prod, {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequest()
      }).pipe();
  }
  upd(prod: Coupon): Observable<string> {
    return this.httpClient
      .put<string>(this.API.concat('/cupons/u'), prod, {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequest()
      }).pipe();
  }
  del(idProd): Observable<string> {
    return this.httpClient
      .delete<string>(`${this.API}/cupons/d/${idProd}`, {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequest()
      }).pipe();
  }
  getAll(pag: string, lim: string): Observable<Coupon[]> {
    return this.httpClient
      .get<Coupon[]>(this.API.concat('/cupons/lstcupons'), {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequestWithOptions(pag, lim)
      }).pipe();
  }
  search(
    pag: string,
    lim: string,
    name: string
  ): Observable<Coupon[]> {
    return this.httpClient
      .get<Coupon[]>(this.API.concat('/cupons/lstcouponsearch'), {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequestSearchWithOptions(pag, lim, name)
      }).pipe();
  }
}
