import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { JoinComponent } from './join.component';
import { SharedModule } from '../shared/shared.module';
import { FormJoinComponent } from './form-join/form-join.component';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    SharedModule
  ],
  declarations: [JoinComponent, FormJoinComponent],
  exports: [JoinComponent, FormJoinComponent]
})
export class JoinModule { }
