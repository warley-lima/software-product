import { Injectable, Output, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BaseAlertMsgUtils {
  @Output() alertMsgEmitt = new EventEmitter<boolean>();  
  @Output() alertBodyMsgEmitt = new EventEmitter<string>();   
  @Output() alertClassCssMsgEmitt = new EventEmitter<string>();
  constructor() { }

  alertMsgEmit(a: boolean) {
    this.alertMsgEmitt.emit(a);
  }
  alertBodyMsgEmit( body: string) {
    this.alertBodyMsgEmitt.emit(body);
  }
  alertClassCssMsgEmit(classCss: string) {
    this.alertClassCssMsgEmitt.emit(classCss);
  }

}
