import { BaseAlertMsgUtils } from './../../shared/Utils/base-alert-msg-utils';
import { Component, OnInit, OnDestroy, EventEmitter, Output } from '@angular/core';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';
import { Subscription } from 'rxjs';

import { Utils } from './../../shared/Utils/Utils';
import { SecurityService } from './../../shared/services/security.service';
import { Redirect } from './../../shared/Utils/Redirect';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {
  formulario: FormGroup;
  inscricao: Subscription;
  constructor(
    private formBuilder: FormBuilder,
    private securityService: SecurityService,
    private redirect: Redirect,
    private alertMsgEvEmitter: BaseAlertMsgUtils,
    private utils: Utils) { }

  ngOnInit() {
   this.formulario = this.formBuilder.group({
      senha: [null, Validators.required],
      email: [null, [Validators.required, Validators.email]]
    });
    this.alertMsgEvEmitter.alertMsgEmitt.emit(false);
  }


  ngOnDestroy() {
    this.inscricao.unsubscribe();
  }

  onSubmit() {
    if (this.formulario.valid) {
       this.getAuthentication();
    } else {
     this.utils.verificaValidacoesForm(this.formulario);
    }
  }

  getAuthentication(): void {
    this.inscricao = this.securityService.getToken(
      this.formulario.get('email').value, this.formulario.get('senha').value)
    .subscribe(
      (user1) => {
        this.securityService.saveToken(user1, 's');
        this.securityService.toggle();
        this.utils.resetar(this.formulario);
        this.redirect.redirectTo('/home');
      },
      (error: any) => {
        this.securityService.saveToken(null, 'n');
       // alert(JSON.stringify(error));
       this.showAlertMsg('Falha ao realizar o Login!', 'alert-danger');
      }
    );
  }

  showAlertMsg(body: string, classAlert: string) {
    this.alertMsgEvEmitter.alertBodyMsgEmitt.emit(body);
    this.alertMsgEvEmitter.alertClassCssMsgEmitt.emit(classAlert);
    this.alertMsgEvEmitter.alertMsgEmitt.emit(true);
  }

  checkErro(campo: string) {
    return this.utils.aplicaCssErro(this.formulario, campo);
  }

  checkTouched(campo: string) {
    return this.utils.verificaValidTouched(this.formulario, campo);
  }

  checkValidField() {
    return this.utils.verificaEmailInvalido( this.formulario );
  }

}
