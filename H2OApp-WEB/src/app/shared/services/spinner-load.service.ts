import { Injectable, Output, EventEmitter } from '@angular/core';
import { Subject } from 'rxjs';
import { LoaderState } from '../Utils/LoaderState';

@Injectable({
  providedIn: 'root'
})
export class SpinnerLoadService {
  @Output() showHideSpinnerEmitter = new EventEmitter<boolean>();

  private loaderSubject = new Subject<LoaderState>();
  loaderState = this.loaderSubject.asObservable();
  constructor() { }

  show() {
    this.loaderSubject.next(<LoaderState>{ show: true });
  }
  hide() {
    this.loaderSubject.next(<LoaderState>{ show: false });
  }


}
