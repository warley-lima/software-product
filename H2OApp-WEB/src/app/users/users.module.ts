import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { UsersRoutingModule } from './users-routing.module';

import { UsersComponent } from './users.component';
import { FormUserComponent } from './form-user/form-user.component';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  imports: [
    CommonModule,
    UsersRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    SharedModule
  ],
  declarations: [UsersComponent, FormUserComponent],
  exports: [UsersComponent, FormUserComponent]
})
export class UsersModule { }
