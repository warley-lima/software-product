import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from '../../guard/auth-guard';
import { PageNotFoundComponent } from '../page-not-found/page-not-found.component';
const routes: Routes = [
  {
    path: 'home',
    loadChildren: 'app/home/home.module#HomeModule',
    canActivate: [AuthGuard],
    canLoad: [AuthGuard]
  },
  {
    path: 'prods',
    loadChildren: 'app/products/products.module#ProductsModule',
    canActivate: [AuthGuard],
    canLoad: [AuthGuard]
  },
  {
    path: 'ords',
    loadChildren: 'app/orders/orders.module#OrdersModule',
    canActivate: [AuthGuard],
    canLoad: [AuthGuard]
  },
  {
    path: 'us',
    loadChildren: 'app/users/users.module#UsersModule',
    canActivate: [AuthGuard],
    canLoad: [AuthGuard]
  },
  {
    path: 'cp',
    loadChildren: 'app/coupons/coupons.module#CouponsModule',
    canActivate: [AuthGuard],
    canLoad: [AuthGuard]
  },
  {
    path: 'conf',
    loadChildren: 'app/configurations/configurations.module#ConfigurationsModule',
    canActivate: [AuthGuard],
    canLoad: [AuthGuard]
  },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HeaderMenuRoutingModule { }
