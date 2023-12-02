import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { environment } from './../../../environments/environment';
import { Company } from './../Model/company';
import { EnderecoERP } from './../Model/enderecoERP';
import { MyCookieService } from '../Utils/MyCookieService';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  private readonly API = `${environment.API}`;
  @Output() showSpinnerEmitter = new EventEmitter<boolean>();

  constructor(private httpClient: HttpClient, private cookieService: MyCookieService) { }

  showSpinner() {
    this.showSpinnerEmitter.emit(true);
  }
  hideSpinner() {
    this.showSpinnerEmitter.emit(false);
  }
  join (company: Company, city: string): Observable<string> {
      const p = new HttpParams()
      .set('cd', city);
      return this.httpClient.post<string>(this.API.concat('/companypub/s'), company, {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: p
      }).pipe();
  }
  queryCNPJ (cnpj: string): Observable<boolean> {
    const p = new HttpParams()
    .set('cn', cnpj);
    return this.httpClient.get<boolean>(this.API.concat('/companypub/q/cnpj'), {
      params: p
    }) .pipe();
  }
  queryMail (mail: string): Observable<boolean> {
    const p = new HttpParams()
    .set('ma', mail);
    return this.httpClient.get<boolean>(this.API.concat('/companypub/q/mail'), {
      params: p
    }) .pipe( );
  }
  getAdress (cep: string): Observable<EnderecoERP> {
    this.showSpinner();
    const p = new HttpParams()
   .set('c', cep);
    return this.httpClient.get<EnderecoERP>(this.API.concat('/query/cep'), {
      params: p
    }).pipe( );
  }

}
