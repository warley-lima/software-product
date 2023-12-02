import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { ProductsRoutingModule } from './products-routing.module';
import { ProductsComponent } from './products.component';
import { SharedModule } from '../shared/shared.module';
import { FormProdComponent } from './form-prod/form-prod.component';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ProductsRoutingModule,
    FormsModule,
    SharedModule
  ],
  declarations: [ProductsComponent, FormProdComponent],
  exports: [ProductsComponent, FormProdComponent]
})
export class ProductsModule { }
