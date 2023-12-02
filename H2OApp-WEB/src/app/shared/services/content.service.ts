import { Injectable } from '@angular/core';
import { OrdersService } from './orders.service';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContentService {
  private content$ = new BehaviorSubject(null);
  constructor(private os: OrdersService) {
    this.os.getsse().subscribe(data => {
      this.content$.next(data);
    });
  }

  findAll() {
    return this.content$;
  }
}
