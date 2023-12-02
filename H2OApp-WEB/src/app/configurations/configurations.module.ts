import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { ConfigurationsRoutingModule } from './configurations-routing.module';
import { ConfigurationsComponent } from './configurations.component';
import { SharedModule } from '../shared/shared.module';
import { DeliveryComponent } from './delivery/delivery.component';
import { FormDeliveryComponent } from './delivery/form-delivery/form-delivery.component';
import { BrandsComponent } from './brands/brands.component';
import { LitersComponent } from './liters/liters.component';
import { FormBrandsComponent } from './brands/form-brands/form-brands.component';
import { FormLitersComponent } from './liters/form-liters/form-liters.component';

@NgModule({
  imports: [
    CommonModule,
    ConfigurationsRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    SharedModule
  ],
  declarations: [ConfigurationsComponent, DeliveryComponent, FormDeliveryComponent,
    BrandsComponent, LitersComponent, FormBrandsComponent, FormLitersComponent],
  exports: [ConfigurationsComponent, DeliveryComponent, FormDeliveryComponent, BrandsComponent,
    LitersComponent, FormBrandsComponent, FormLitersComponent]
})
export class ConfigurationsModule { }
