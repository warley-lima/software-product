import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { OrdersService } from '../../services/orders.service';

@Component({
  selector: 'app-sse',
  templateUrl: './sse.component.html',
  styleUrls: ['./sse.component.scss']
})
export class SseComponent implements OnInit, OnDestroy {
  inscricao: Subscription;
  isShow: boolean;
  constructor( private orderService: OrdersService) { }

  ngOnInit() {
   this.newOrder();
  }
  ngOnDestroy() {
   // this.inscricao.unsubscribe();
   }
   close() {
    this.isShow = false;
   }
  newOrder() {
    this.orderService.connect();
    this.inscricao = this.orderService.newOrder.subscribe(a => {
      this.isShow = a;
    });
 }

}
