import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { SecurityService } from './services/security.service';
// import { AuthenticationComponent } from '../authentication/authentication.component';
import { Utils } from './Utils/Utils';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CampoControlErroComponent } from './Components/campo-control-erro/campo-control-erro.component';
import { FormDebugComponent } from './Components/form-debug/form-debug.component';
import { InputFieldComponent } from './Components/input-field/input-field.component';
import { ErrorMsgComponent } from './Components/error-msg/error-msg.component';
import { PageNotFoundComponent } from './Components/page-not-found/page-not-found.component';
import { HeaderComponent } from './Components/header/header.component';
import { HeaderMenuRoutingModule } from './Components/header/header-menu-routing.module';
import { ItemWIconComponent } from './Components/item-w-icon/item-w-icon.component';
import { SidebarMenuComponent } from './Components/sidebar-menu/sidebar-menu.component';
import { ToolbarComponent } from './Components/toolbar/toolbar.component';
import { ChildrensGuard } from './guard/childrens-guard';
import { BaseModalComponent } from './Components/base-modal/base-modal.component';
import { SelectFieldComponent } from './Components/select-field/select-field.component';
import { BaseAlertModalComponent } from './Components/base-alert-modal/base-alert-modal.component';
import { SpinnerLoaderComponent } from './Components/spinner-loader/spinner-loader.component';
import { BaseTableComponent } from './Components/base-table/base-table.component';
import { SseComponent } from './Components/sse/sse.component';
import { BaseAlertMsgComponent } from './Components/base-alert-messages/base-alert-msg/base-alert-msg.component';
import { DashboardComponent } from './Components/dashboard/dashboard.component';
import { BaseListGroupComponent } from './Components/base-list-group/base-list-group.component';
@NgModule({
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
    HeaderMenuRoutingModule
  ],
 declarations: [
  // AuthenticationComponent,
   CampoControlErroComponent,
   FormDebugComponent,
   InputFieldComponent,
   ErrorMsgComponent,
   PageNotFoundComponent,
   HeaderComponent,
   ItemWIconComponent,
   SidebarMenuComponent,
   ToolbarComponent,
   BaseModalComponent,
   SelectFieldComponent,
   BaseAlertModalComponent,
   SpinnerLoaderComponent,
   BaseTableComponent,
   SseComponent,
   BaseAlertMsgComponent,
   DashboardComponent,
   BaseListGroupComponent
   ],
  exports: [
   // AuthenticationComponent,
    CampoControlErroComponent,
    FormDebugComponent,
    InputFieldComponent,
    ErrorMsgComponent,
    PageNotFoundComponent,
    HeaderComponent,
    ItemWIconComponent,
    SidebarMenuComponent,
    ToolbarComponent,
    BaseModalComponent,
    SelectFieldComponent,
    BaseAlertModalComponent,
    SpinnerLoaderComponent,
    BaseTableComponent,
    SseComponent,
    BaseAlertMsgComponent,
    DashboardComponent,
    BaseListGroupComponent
  ],
  providers: [SecurityService, Utils, ChildrensGuard]
})
export class SharedModule {
 }
