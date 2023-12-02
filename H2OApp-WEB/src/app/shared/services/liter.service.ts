import { Injectable, EventEmitter, Output } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from './../../../environments/environment';
import { Liter } from './../Model/liter';
import { SecurityService } from './security.service';
import { MyCookieService } from '../Utils/MyCookieService';

@Injectable({
  providedIn: 'root'
})
export class LiterService {
  private readonly API = `${environment.API}`;
  private liters: Observable<Liter[]>;
  private liters2: Liter[];
  @Output() literEventEmitter = new EventEmitter<Liter[]>();
  @Output() literCompanyEventEmitter = new EventEmitter<Liter[]>();
  @Output() literRefreshEmitter = new EventEmitter<boolean>();

  constructor(private httpClient: HttpClient,
    private cookieService: MyCookieService,
    private sec: SecurityService) { }

  emittLitersCompany(brandsComp: Liter[]) {
    this.literCompanyEventEmitter.emit(brandsComp);
  }
  refreshTable() {
    this.literRefreshEmitter.emit(true);
  }
  getLitersLocal(): Observable<Liter[]> {
    if (this.liters) {
      this.liters = this.getLiters();
      return this.liters;
    } else {
       return this.liters;
    }
  }
  getLitersLocal2(b: Liter[]) {
    this.liters2 = b;
    this.literEventEmitter.emit(this.liters2);
  }
  getNameLiter(id: number) {
    if (this.liters2 !== undefined && id !== undefined) {
      for (let i = 0; i < this.liters2.length; i++) {
       if (this.liters2[i].idL === id) {
         const name = this.liters2[i].nmL;
          i = this.liters2.length;
          return name;
       }
      }
    }
  }
  getLiters(): Observable<Liter[]> {
    return this.httpClient.get<Liter[]>(this.API.concat('/literspub/l'), {
      headers: new HttpHeaders({
        'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
      }),
      params: this.sec.getParamsToRequest()
    });
  }
  add(prod: number[]): Observable<string> {
    return this.httpClient
      .post<string>(this.API.concat('/company_liters/s'), prod, {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequest()
      })
      .pipe();
  }
  getLitersCompany(pag: string, lim: string): Observable<Liter[]> {
    return this.httpClient.get<Liter[]>(this.API.concat('/company_liters/lst'), {
      headers: new HttpHeaders({
        'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
      }),
      params: this.sec.getParamsToRequestWithOptions(pag, lim)
   });
  }
  getLitersCompanySelected(): Observable<number[]> {
      return this.httpClient.get<number[]>(this.API.concat('/company_liters/lstliterselected'), {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequest()
     });
  }
  del(idProd): Observable<string> {
    return this.httpClient
      .delete<string>(`${this.API}/company_liters/d/${idProd}`, {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequest()
      })
      .pipe();
  }
}
