import { Injectable, EventEmitter, Output } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from './../../../environments/environment';
import { Brand } from './../Model/brand';
import { SecurityService } from './security.service';
import { MyCookieService } from './../Utils/MyCookieService';

@Injectable({
  providedIn: 'root'
})
export class BrandService {
  private readonly API = `${environment.API}`;
  private brands: Observable<Brand[]>;
  private brands2: Brand[];
  @Output() brandEventEmitter = new EventEmitter<Brand[]>();
  @Output() brandCompanyEventEmitter = new EventEmitter<Brand[]>();
  @Output() brandsRefreshEmitter = new EventEmitter<boolean>();

  constructor(
    private httpClient: HttpClient,
    private cookieService: MyCookieService,
    private secBrands: SecurityService
    ) { }

  emittBrandsCompany(brandsComp: Brand[]) {
    this.brandCompanyEventEmitter.emit(brandsComp);
  }
  refreshTable() {
    this.brandsRefreshEmitter.emit(true);
  }
 /* getBrandsLocal(): Observable<Brand[]> {
    if (undefined === this.brands) {
      this.brands = this.getAllBrandsByCompany();
      return this.brands;
    } else {
       return this.brands;
    }
  } */
 /* getBrandsLocal() {
    if (undefined === this.brands2) {
      this.getAllBrandsByCompany();
      return this.brands;
    } else {
       return this.brands;
    }
  } */

   hasBrandsLocal(): boolean {
    if (undefined === this.brands2 || null === this.brands2) {
      return false;
    } else {
       return true;
    }
   }
  getBrandsLocal(): Brand[] {
    if (undefined === this.brands2 || null === this.brands2) {
      return null;
    } else {
       return this.brands2;
    }
  }

  getBrandsLocal2(b: Brand[]) {
    this.brands2 = b;
    this.brandEventEmitter.emit(this.brands2);
  }
  getNameBrand(id: number) {
    if (this.brands2 !== undefined && id !== undefined) {
      for (let i = 0; i < this.brands2.length; i++) {
       if (this.brands2[i].id === id) {
         const name = this.brands2[i].nm;
          i = this.brands2.length;
          return name;
       }
      }
    }
  }

  setBrands(): void {
    this.secBrands = null;
  }
 /* getBrandsToken() {
    return this.secBrands.getAcToken();
  }*/

  getBrands(): Observable<Brand[]> {
      return this.httpClient.get<Brand[]>(this.API.concat('/brands/getAll'), {
      headers: new HttpHeaders({
        'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
      }),
       params: this.secBrands.getParamsToRequest()
    }).pipe();
  }
  add(prod: number[]): Observable<string> {
    return this.httpClient
      .post<string>(this.API.concat('/company_brand/s'), prod, {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.secBrands.getParamsToRequest()
      })
      .pipe();
  }
  getBrandsCompany(pag: string, lim: string): Observable<Brand[]> {
    return this.httpClient.get<Brand[]>(this.API.concat('/company_brand/lst'), {
      headers: new HttpHeaders({
        'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
      }),
      params: this.secBrands.getParamsToRequestWithOptions(pag, lim)
   }).pipe();
  }

  getAllBrandsByCompany(): Observable<Brand[]> {
    return this.httpClient.get<Brand[]>(this.API.concat('/company_brand/lstbrands'), {
      headers: new HttpHeaders({
        'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
      }),
      params: this.secBrands.getParamsToRequest()
   }).pipe();
  }
 // Uncaught Error: Extension context invalidated.
  getBrandsCompanySelected(): Observable<number[]> {
      return this.httpClient.get<number[]>(this.API.concat('/company_brand/lstbrandselected'), {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.secBrands.getParamsToRequest()
    }).pipe();
   }

   del(idProd): Observable<string> {
    return this.httpClient
      .delete<string>(`${this.API}/company_brand/d/${idProd}`, {
        headers: new HttpHeaders({
          'X-XSRF-TOKEN':	this.cookieService.get('XSRF-TOKEN')
        }),
        params: this.secBrands.getParamsToRequest()
      })
      .pipe();
  }
}
