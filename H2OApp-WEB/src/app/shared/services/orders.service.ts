import { Injectable, EventEmitter, Output, NgZone } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { environment } from './../../../environments/environment';
import { SecurityService } from './security.service';
import { OrderTable } from '../Model/orderTable';
import { Order } from '../Model/order';
import { MyCookieService } from '../Utils/MyCookieService';
const EventSource: any = window['EventSource'];

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  private readonly API = `${environment.API}`;
 /* private data = {
    access_token: this.sec.getAcToken()
  }; */

  @Output() couponEventEmitter = new EventEmitter<Order>();
  @Output() couponRefreshEmitter = new EventEmitter<boolean>();
  @Output() newOrder = new EventEmitter<boolean>();
  constructor(private httpClient: HttpClient,
    private cookieService: MyCookieService,
    private sec: SecurityService,
    private zone: NgZone) {}

  emittNew(id) {
    this.couponEventEmitter.emit(id);
  }
  update(id) {
    this.couponEventEmitter.emit(id);
  }
  refreshTable() {
    this.couponRefreshEmitter.emit(true);
  }
  upd(prod: OrderTable): Observable<string> {
    return this.httpClient
      .put<string>(this.API.concat('/order/u'), prod, {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
         params: this.sec.getParamsToRequest()
      })
      .pipe();
  }
  getAll(pag: string, lim: string): Observable<OrderTable[]> {
    return this.httpClient
      .get<OrderTable[]>(this.API.concat('/order/lstorder'), {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
         params: this.sec.getParamsToRequestWithOptions(pag, lim)
      })
      .pipe();
  }

  search(pag: string, lim: string, name: string): Observable<OrderTable[]> {
    return this.httpClient
      .get<OrderTable[]>(this.API.concat('/order/lstordersearch'), {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequestSearchWithOptions(pag, lim, name)
      })
      .pipe();
  }

  sse(): Observable<string> {
   // const p = new HttpParams()
   // .set('access_token', this.sec.getAcToken());
    return this.httpClient
      .get<string>(this.API.concat('/orderpub/rbe2'))
      .pipe();
  }
  public sseEvent(): Observable<string> {
    return new Observable<string>(obs => {
      const es = new EventSource(this.API.concat('/orderpub/rbe3'), {withCredentials: true});
      es.addEventListener('m', (evt) => {
        obs.next(evt.data);
      });
      return () => es.close();
    });
  }
  getsse(): Observable<string> {
    const p = new HttpParams()
    .set('access_token', this.sec.getAcToken());
      return new Observable<any>(obs => {
        const es = new EventSource(this.API.concat('/orderemit/sse2?')
        .concat(p.toString()));
        es.onmessage = evt => {
          const data = JSON.parse(evt.data);
          this.zone.run(() => obs.next(data));
        };
        return () => es.close();
      });
  }
  connect(): void  {
    const source = new EventSource(this.API.concat('/orderpub/rbe2'));
  // const source = new EventSource('http://35.155.157.83:8080/api/api/orderpub/rbe2');
    console.log(' Criando uma Conexão...');
    // source.
    source.addEventListener('m', id => {
        if (null != this.sec.getIdCompany() && id.data === this.sec.getIdCompany()) {
         this.newOrder.emit(true);
        } else {
          this.newOrder.emit(false);
        }
     });
   }
  getOrder(idProd): Observable<Order> {
    return this.httpClient
      .get<Order>(this.API.concat(`/order/o/${idProd}`), {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequest()
      })
      .pipe();
  }
}
