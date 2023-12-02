import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CanActivateChild, ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';

@Injectable()
export class ChildrensGuard implements CanActivateChild {
  canActivateChild(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | boolean {
    if (state.url.includes('editar')) {
      // alert('Usuário sem acesso');
      // return Observable.of(false);
    }

    return true;
  }
}
