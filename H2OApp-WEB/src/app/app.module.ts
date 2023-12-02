import { BrowserModule } from '@angular/platform-browser';
import { NgModule , NO_ERRORS_SCHEMA, LOCALE_ID} from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule, HttpClientXsrfModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';

import { SharedModule } from './shared/shared.module';
import { AuthModule } from './auth/auth.module';
import { LoaderInterceptorService } from './shared/Utils/LoaderInterceptorService';
import { registerLocaleData } from '@angular/common';
import localePt from '@angular/common/locales/pt';
import { JoinModule } from './join/join.module';
// import { HttpXsrfInterceptor } from '@angular/common/http/src/xsrf';
// import { HttpXsrfInterceptor, HttpXsrfTokenExtractor, HttpXsrfCookieExtractor,
//  XSRF_COOKIE_NAME, XSRF_HEADER_NAME } from '@angular/common/http/src/xsrf';
registerLocaleData(localePt);

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    // HttpClientXsrfModule,
    SharedModule,
    AuthModule,
    JoinModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'XSRF-TOKEN',
      headerName: 'XSRF-TOKEN'
    })
  ],
  schemas: [ NO_ERRORS_SCHEMA ],
  exports: [
    MDBBootstrapModule
  ],
providers: [
  {
    provide: HTTP_INTERCEPTORS,
    useClass: LoaderInterceptorService,
    multi: true
  },
  [ CookieService ],

  // [{provide: HTTP_INTERCEPTORS, useClass: HttpXsrfInterceptor, multi: true }],
  { provide: LOCALE_ID, useValue: 'pt-BR' },
 // { provide: HTTP_INTERCEPTORS, useExisting: HttpXsrfInterceptor, multi: true },
 // { provide: HttpXsrfTokenExtractor, useClass: HttpXsrfCookieExtractor }/*,
 /* { provide: XSRF_COOKIE_NAME, useValue: 'XSRF-TOKEN' },
  { provide: XSRF_HEADER_NAME, useValue: 'X-XSRF-TOKEN' }*/
],
  bootstrap: [AppComponent]
})
export class AppModule { }
