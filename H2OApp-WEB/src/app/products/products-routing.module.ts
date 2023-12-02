import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FormProdComponent } from './form-prod/form-prod.component';
import { ChildrensGuard } from '../shared/guard/childrens-guard';
import { ProductsComponent } from './products.component';
const routes: Routes = [{
  path: '', component: ProductsComponent,
  canActivateChild: [ChildrensGuard],
  children: [
      { path: 'new', component: FormProdComponent }
  ]
}
,
 {path: 'new2', component: FormProdComponent}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductsRoutingModule { }
