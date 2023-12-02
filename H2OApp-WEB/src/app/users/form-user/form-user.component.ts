import { BaseAlertMsgUtils } from './../../shared/Utils/base-alert-msg-utils';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription} from 'rxjs';
import { FormBuilder, Validators } from '@angular/forms';

import { BaseFormComponent } from './../../shared/Components/base-form/base-form.component';
import { UserServer } from './../../shared/Model/userServer';
import { UserService } from './../../shared/services/user.service';

@Component({
  selector: 'app-form-user',
  templateUrl: './form-user.component.html',
  styleUrls: ['./form-user.component.scss']
})
export class FormUserComponent extends BaseFormComponent
  implements OnInit, OnDestroy {
  private inscUserEmiter: Subscription;
  private isUpdate: boolean;
  private idUser: number;

  constructor(
    private formBuilder: FormBuilder,
    private alertMsgEvEmitter: BaseAlertMsgUtils,
    private userService: UserService
  ) {super(); }

  ngOnInit() {
    this.formulario = this.formBuilder.group({
      nome: [
        null,
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(125)
        ]
      ],
      password: [
        null,
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(125)
        ]
      ],
      confPassword: [
        null,
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(125)
        ]
      ],
      email: [
        null,
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(125)
        ]
      ]
    });

    this.inscUserEmiter = this.userService.userEventEmitter.subscribe(p => {
      if (null !== p) {
        this.isUpdate = true;
        this.idUser = p.idU;
        this.populaDadosForm(p);
      } else {
        this.isUpdate = false;
        this.idUser = 0;
        this.resetaDadosForm();
      }
    });
    this.alertMsgEvEmitter.alertMsgEmitt.emit(false);
  }
  ngOnDestroy(): void {
    this.inscUserEmiter.unsubscribe();
  }

  submit() {
    if (this.isUpdate) {
      this.userService
        .updUser(
          new UserServer(
            this.idUser,
            this.formulario.get('nome').value,
            this.formulario.get('email').value,
            this.formulario.get('password').value
          )
        )
        .subscribe(p => {
          if ('S;' === p['ret']) {
            this.showAlertMsg('Cadastro realizado com Sucesso! :)', 'alert-success');
            this.resetaDadosForm();
            this.userService.refreshUserTable();
          } else if (null === p['ret'] || 'N;' === p['ret']) {
            this.showAlertMsg('Falha ao realizar o cadastro!', 'alert-danger');
          }
        });
    } else {
      this.userService
        .addUser(
          new UserServer(
            this.idUser,
            this.formulario.get('nome').value,
            this.formulario.get('email').value,
            this.formulario.get('password').value
          )
        )
        .subscribe(p => {
          if ('S;' === p['ret']) {
            this.showAlertMsg('Cadastro realizado com Sucesso! :)', 'alert-success');
            this.resetaDadosForm();
            this.userService.refreshUserTable();
          } else if (null === p['ret'] || 'N;' === p['ret']) {
            this.showAlertMsg('Falha ao realizar o cadastro!', 'alert-danger');
          }
        });
    }
  }
  populaDadosForm(p) {
    this.formulario.patchValue({
      nome: p.nm,
      email: p.em,
      password: p.pa,
      confPassword: p.pa
    });
  }
  resetaDadosForm() {
    this.formulario.patchValue({
      nome: null,
      email: null,
      password: null,
      confPassword: null
    });
  }
  compararCargos(obj1, obj2) {
    return obj1 && obj2
      ? obj1.nome === obj2.nome && obj1.nivel === obj2.nivel
      : obj1 === obj2;
  }
  showAlertMsg(body: string, classAlert: string) {
    this.alertMsgEvEmitter.alertBodyMsgEmitt.emit(body);
    this.alertMsgEvEmitter.alertClassCssMsgEmitt.emit(classAlert);
    this.alertMsgEvEmitter.alertMsgEmitt.emit(true);
  }
}
