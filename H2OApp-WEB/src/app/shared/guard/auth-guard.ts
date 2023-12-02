import { Redirect } from './../Utils/Redirect';
import { Observable } from 'rxjs/Observable';
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, CanLoad, Route } from '@angular/router';
import { SecurityService } from '../services/security.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanLoad {
  constructor(
    private redirect: Redirect,
    private securityService: SecurityService
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | boolean {
   // console.log('AuthGuard');
    return this.verificarAcesso();
  }

  private verificarAcesso() {
  // console.log('verificarAcesso:');
    if (this.securityService.isLogged()) {
      return true;
    } else {
      this.redirect.redirectTo('/login');
      return false;
    }
  }

  canLoad(route: Route): Observable<boolean> | boolean {
    return this.verificarAcesso();
  }
}
