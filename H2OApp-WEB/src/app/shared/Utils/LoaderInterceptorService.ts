import { Injectable, InjectionToken, Inject, PLATFORM_ID } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse,
  HttpXsrfTokenExtractor } from '@angular/common/http';
import { DOCUMENT , isPlatformBrowser} from '@angular/common';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
// import { CookieService } from 'ngx-cookie-service';
import { SpinnerLoadService } from '../services/spinner-load.service';

@Injectable({
  providedIn: 'root'
})
export class LoaderInterceptorService implements HttpInterceptor {
  private documentIsAccessible: boolean;

  constructor(// private tokenExtractor: HttpXsrfTokenExtractor,
  //  private cookieService: CookieService,
    private loaderService: SpinnerLoadService,
    // private documentIsAccessible: boolean,
    @Inject( DOCUMENT ) private document: any,
    @Inject( PLATFORM_ID ) private platformId: InjectionToken<Object>,
    ) {
      this.documentIsAccessible = isPlatformBrowser( this.platformId );
     }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    this.showLoader();
   // const DOCUMENT: InjectionToken<Document>;
    return next.handle(req).pipe(tap((event: HttpEvent<any>) => {
      /*
      // alert('Hope--> ' + this.get('XSRF-TOKEN'));
    //  console.log('Tdos' + this.getAll());
     let requestMethod: string = req.method;
      requestMethod = requestMethod.toLowerCase();

      if (requestMethod && (requestMethod === 'post' ||
      requestMethod === 'delete' || requestMethod === 'put' )) {
         // console.log(' PASSEI AUI LORD');
          const headerName = 'X-XSRF-TOKEN';
          // const token = this.tokenExtractor.getToken() as string;
          const token =  this.get('XSRF-TOKEN'); // this.tokenExtractor.getToken();

        //  alert('TOKEN--> ' + this.document.cookie);
         // alert('Hope--> ' + this.getCookie('XSRF-TOKEN'));
        //  alert('Jesus--> ' + this.cookieService.get('XSRF-TOKEN'));
          if (token !== null && !req.headers.has(headerName)) {
            alert('COOKIE --> ' + token);
            req = req.clone({ headers: req.headers.set(headerName, token) });
          }
         // req = req.clone({ headers: req.headers.set(headerName, '733e4db3-79c5-4e73-8de9-d88be242f527') });
       }
      if (event instanceof HttpResponse) {
        this.onEnd();
      }
     return next.handle(req);*/
     if (event instanceof HttpResponse) {
        this.onEnd();
      }
    },
      (err: any) => {
        this.onEnd();
    }));
  }


  private onEnd(): void {
    this.hideLoader();
  }

  private showLoader(): void {
    this.loaderService.show();
  }

  private hideLoader(): void {
    this.loaderService.hide();
  }

/*
  getCookie(name) {
    const splitCookie = this.document.cookie.split('; ');
    for (let i = 0; i < splitCookie.length; i++) {
      console.log('HELP ME JESUS!' + splitCookie[i].split('='));
     const splitValue = splitCookie[i].split('=');
      if (splitValue[0] === name) {
        return splitValue[1];
      }
    }
    return '';
   }




  check( name: string ): boolean {
    if ( !this.documentIsAccessible ) {
      return false;
    }
    name = encodeURIComponent( name );
    const regExp: RegExp = this.getCookieRegExp( name );
    const exists: boolean = regExp.test( this.document.cookie );
    return exists;
  }

  get( name: string ): string {
    if ( this.documentIsAccessible && this.check( name ) ) {
      name = encodeURIComponent( name );
      const regExp: RegExp = this.getCookieRegExp( name );
      const result: RegExpExecArray = regExp.exec( this.document.cookie );
      return decodeURIComponent( result[ 1 ] );
    } else {
      return '';
    }
  }

  getAll(): {} {
    if ( !this.documentIsAccessible ) {
      return {};
    }
    const cookies: {} = {};
    const document: any = this.document;
    if ( document.cookie && document.cookie !== '' ) {
      const split: Array<string> = document.cookie.split(';');
      for ( let i = 0; i < split.length; i += 1 ) {
        const currentCookie: Array<string> = split[ i ].split('=');
        currentCookie[ 0 ] = currentCookie[ 0 ].replace( /^ /, '' );
        cookies[ decodeURIComponent( currentCookie[ 0 ] ) ] = decodeURIComponent( currentCookie[ 1 ] );
      }
    }
    return cookies;
  }

  set(
    name: string,
    value: string,
    expires?: number | Date,
    path?: string,
    domain?: string,
    secure?: boolean,
    sameSite?: 'Lax' | 'Strict'
  ): void {
    if ( !this.documentIsAccessible ) {
      return;
    }

    let cookieString: string = encodeURIComponent( name ) + '=' + encodeURIComponent( value ) + ';';
    if ( expires ) {
      if ( typeof expires === 'number' ) {
        const dateExpires: Date = new Date( new Date().getTime() + expires * 1000 * 60 * 60 * 24 );
        cookieString += 'expires=' + dateExpires.toUTCString() + ';';
      } else {
        cookieString += 'expires=' + expires.toUTCString() + ';';
      }
    }

    if ( path ) {
      cookieString += 'path=' + path + ';';
    }

    if ( domain ) {
      cookieString += 'domain=' + domain + ';';
    }

    if ( secure ) {
      cookieString += 'secure;';
    }

    if ( sameSite ) {
      cookieString += 'sameSite=' + sameSite + ';';
    }

    this.document.cookie = cookieString;
  }

  delete( name: string, path?: string, domain?: string ): void {
    if ( !this.documentIsAccessible ) {
      return;
    }
    this.set( name, '', new Date('Thu, 01 Jan 1970 00:00:01 GMT'), path, domain );
  }

  deleteAll( path?: string, domain?: string ): void {
    if ( !this.documentIsAccessible ) {
      return;
    }
    const cookies: any = this.getAll();
    for ( const cookieName in cookies ) {
      if ( cookies.hasOwnProperty( cookieName ) ) {
        this.delete( cookieName, path, domain );
      }
    }
  }

  private getCookieRegExp( name: string ): RegExp {
    const escapedName: string = name.replace( /([\[\]\{\}\(\)\|\=\;\+\?\,\.\*\^\$])/ig, '\\$1' );
    return new RegExp( '(?:^' + escapedName + '|;\\s*' + escapedName + ')=(.*?)(?:;|$)', 'g' );
  }*/
}
