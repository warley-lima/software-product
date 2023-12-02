import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { OrdersRoutingModule } from './orders-routing.module';
import { OrdersComponent } from './orders.component';
import { SharedModule } from '../shared/shared.module';
import { FormUpdOrderComponent } from './form-upd-order/form-upd-order.component';
import { DetOrderComponent } from './det-order/det-order.component';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    OrdersRoutingModule,
    SharedModule
  ],
  declarations: [OrdersComponent, FormUpdOrderComponent, DetOrderComponent],
  exports: [OrdersComponent, FormUpdOrderComponent, DetOrderComponent]
})
export class OrdersModule { }
