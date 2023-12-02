import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { BaseAlertMsgUtils } from './../../../Utils/base-alert-msg-utils';

@Component({
  selector: 'app-base-alert-msg',
  template: `
  <div *ngIf=showMsg class="col-12 col-md-12 alert {{ myClassAlert }} alert-dismissible fade show" role="alert">
    <strong>{{body}}</strong>
    <button type="button" class="close" aria-label="Close" (click) = close()>
      <span aria-hidden="true">&times;</span>
    </button>
  </div>
  `,
  styles: [
    `
    h5, .h5 {
      font-size: 1.25rem;
      font-weight: 500;
  }
    `
  ]
})
export class BaseAlertMsgComponent implements OnInit, OnDestroy {
  body: string;
  myClassAlert: string;
  showMsg: boolean;
  inscAlertMsg: Subscription;
  constructor(private alertMsgEvEmitter: BaseAlertMsgUtils) { }

  ngOnInit() {
    this.inscAlertMsg = this.alertMsgEvEmitter.alertBodyMsgEmitt.subscribe(b => {
        this.body = b;
    });
     this.inscAlertMsg = this.alertMsgEvEmitter.alertClassCssMsgEmitt.subscribe(c => {
        this.myClassAlert = c;
    });
    this.inscAlertMsg = this.alertMsgEvEmitter.alertMsgEmitt.subscribe(p => {
        if (true === p) {
          this.show();
        } else if (false === p) {
          this.body = null;
          this.myClassAlert = null;
          this.close();
        }
    });
  }

 close() {
    this.showMsg = false;
 }

 show() {
  this.showMsg = true;
}

 ngOnDestroy() {
   this.inscAlertMsg.unsubscribe();
 }


}
