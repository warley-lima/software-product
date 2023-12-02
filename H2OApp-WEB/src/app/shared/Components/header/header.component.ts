import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { Redirect } from '../../Utils/Redirect';
import { SecurityService } from '../../services/security.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit, OnDestroy {
  uName: string;
  inscricao: Subscription;
  isShow: boolean;
  constructor(
    private securityService: SecurityService,
    private redirect: Redirect) { }

  ngOnInit() {
    this.isShow = this.securityService.isLogged();
    this.uName = this.securityService.getNameLogged();
    this.inscricao = this.securityService.change.subscribe(nm => {
      this.uName = nm;
      this.isShow = true;
    });
  }

  ngOnDestroy() {
    this.inscricao.unsubscribe();
  }

  logOut() {
     if (this.securityService.logOut()) {
      this.isShow = false;
      this.uName = this.securityService.getNameLogged();
      this.redirect.redirectTo('/login');
    }
  }

}
