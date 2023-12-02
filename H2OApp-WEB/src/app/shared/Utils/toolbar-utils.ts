import { Injectable, Output, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ToolbarUtils {
  @Output() clicked = new EventEmitter<string>();
  constructor() { }

  clickItemToolbarEmitter(a: string) {
    this.clicked.emit(a);
  }
}
