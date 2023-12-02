import { Injectable, EventEmitter, Output } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from './../../../environments/environment';
import { Delivery } from './../Model/delivery';
import { SecurityService } from './security.service';
import { MyCookieService } from './../Utils/MyCookieService';

@Injectable({
  providedIn: 'root'
})
export class DeliveryService {
  private readonly API = `${environment.API}`;

  @Output() deliveryEventEmitter = new EventEmitter<Delivery>();
  @Output() deliveryRefreshEmitter = new EventEmitter<boolean>();

  constructor(private httpClient: HttpClient,
    private cookieService: MyCookieService,
    private sec: SecurityService) { }

  emittNew() {
    this.deliveryEventEmitter.emit(null);
  }
  update(p: Delivery) {
    this.deliveryEventEmitter.emit(p);
  }
  refreshTable() {
    this.deliveryRefreshEmitter.emit(true);
  }
  add(prod: Delivery): Observable<string> {
    return this.httpClient
      .post<string>(this.API.concat('/company_actuaction/s'), prod,  {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequest()
       }).pipe();
  }
  upd(prod: Delivery): Observable<string> {
    return this.httpClient
      .put<string>(this.API.concat('/company_actuaction/u'), prod, {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequest()
      }).pipe();
  }
  del(idProd): Observable<string> {
    return this.httpClient
      .delete<string>(`${this.API}/company_actuaction/d/${idProd}`, {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequest()
      }).pipe();
  }
  getAll(pag: string, lim: string): Observable<Delivery[]> {
    return this.httpClient
      .get<Delivery[]>(this.API.concat('/company_actuaction/lst'), {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequestWithOptions(pag, lim)
      }).pipe();
  }

}
