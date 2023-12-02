import { Injectable, Output, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TableUtils {
  @Output() clickedPag = new EventEmitter<number>();
  @Output() clickedSer = new EventEmitter<string>();
  constructor() { }

  clickPaginationTableEmitter(a: number) {
    this.clickedPag.emit(a);
  }

  clickSearchTableEmitter(a: string) {
    this.clickedSer.emit(a);
  }

}
