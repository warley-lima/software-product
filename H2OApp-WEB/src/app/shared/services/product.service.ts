import { Injectable, EventEmitter, Output } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { environment } from './../../../environments/environment';
import { Product } from '../Model/product';
import { SecurityService } from './security.service';
import { throwError } from 'rxjs';
import { MyCookieService } from '../Utils/MyCookieService';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private readonly API = `${environment.API}`;
  /*private httpOptions = {
    headers: new HttpHeaders({
    })
  };*/
  @Output() prodEventEmitter = new EventEmitter<Product>();
  @Output() prodRefreshEmitter = new EventEmitter<boolean>();
  constructor(private httpClient: HttpClient,
    private cookieService: MyCookieService,
    private sec: SecurityService) {}

    getProduct(userName: string, password: string): Observable<any> {
      return this.httpClient.get<any>(this.API.concat('/login'), {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequest()
      });
    }
    emittProduct() {
     this.prodEventEmitter.emit(null);
    }
    updateProduct(p: Product) {
      this.prodEventEmitter.emit(p);
    }
    refreshProductTable() {
     this.prodRefreshEmitter.emit( true);
    }
   /* addProduct3 (prod: Product): Observable<Product> {
      return this.httpClient.post<Product>(this.API.concat('/products/s'), prod, this.httpOptions)
      .pipe();
    } */
    addProduct (prod: Product): Observable<string> {
      return this.httpClient.post<string>(this.API.concat('/products/s'), prod, {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequest()
      }) .pipe();
    }
    updProduct (prod: Product): Observable<string> {
      return this.httpClient.put<string>(this.API.concat('/products/u'), prod, {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequest()
      }) .pipe();
    }
    delProduct (idProd): Observable<string> {
      return this.httpClient.delete<string>(`${this.API}/products/d/${idProd}`, {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequest()
      }) .pipe();
    }
    getProducts (pag: string, lim: string): Observable<Product[]> {
      return this.httpClient.get<Product[]>(this.API.concat('/products/lstprod'), {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequestWithOptions(pag, lim)
      }) .pipe();
    }
    searchProducts (pag: string, lim: string, name: string): Observable<Product[]> {
      return this.httpClient.get<Product[]>(this.API.concat('/products/lstprodsearch'), {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.sec.getParamsToRequestSearchWithOptions(pag, lim, name)
      }) .pipe();
    }
    private handleError(error: HttpErrorResponse) {
      if (error.error instanceof ErrorEvent) {
        console.error('An error occurred:', error.error.message);
      } else {
        console.error(
          `Backend returned code ${error.status}, ` +
          `body was: ${error.error}`);
      }
      return throwError(
        'Something bad happened; please try again later.');
    }

}
