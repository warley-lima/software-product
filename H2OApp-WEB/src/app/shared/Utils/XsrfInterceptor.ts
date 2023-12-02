import { Injectable} from '@angular/core';
import { HttpInterceptor, HttpXsrfTokenExtractor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class XsrfInterceptor implements HttpInterceptor {

    constructor(private tokenExtractor: HttpXsrfTokenExtractor) {
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

      let requestMethod: string = req.method;
      requestMethod = requestMethod.toLowerCase();

      if (requestMethod && (requestMethod === 'POST' || requestMethod === 'post' ||
      requestMethod === 'delete' || requestMethod === 'put' )) {
          console.log(' PASSEI AUQI LORD');
          const headerName = 'X-XSRF-TOKEN';
          const token = this.tokenExtractor.getToken() as string;
          if (token !== null && !req.headers.has(headerName)) {
            req = req.clone({ headers: req.headers.set(headerName, token) });
          }
       }

     return next.handle(req);
    }
}
