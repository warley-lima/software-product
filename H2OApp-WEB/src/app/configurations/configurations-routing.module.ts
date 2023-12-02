import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ChildrensGuard } from '../shared/guard/childrens-guard';
import { ConfigurationsComponent } from './configurations.component';

const routes: Routes = [{
  path: '', component: ConfigurationsComponent,
  canActivateChild: [ChildrensGuard],
  children: [
      { path: 'new', component: ConfigurationsComponent }
  ]
}
,
 {path: 'new2', component: ConfigurationsComponent}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ConfigurationsRoutingModule { }
