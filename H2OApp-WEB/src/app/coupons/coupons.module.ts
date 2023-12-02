import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { CouponsRoutingModule } from './coupons-routing.module';
import { CouponsComponent } from './coupons.component';
import { FormCouponComponent } from './form-coupon/form-coupon.component';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  imports: [
    CommonModule,
    CouponsRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    SharedModule
  ],
  declarations: [CouponsComponent, FormCouponComponent],
  exports: [CouponsComponent, FormCouponComponent]
})
export class CouponsModule { }
