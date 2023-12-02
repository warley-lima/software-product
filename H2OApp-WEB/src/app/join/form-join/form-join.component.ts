
import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

import { BaseFormComponent } from './../../shared/Components/base-form/base-form.component';
import { CompanyService } from './../../shared/services/company.service';
import { Company } from './../../shared/Model/company';
import { BaseAlertMsgUtils } from './../../shared/Utils/base-alert-msg-utils';
@Component({
  selector: 'app-form-join',
  templateUrl: './form-join.component.html',
  styleUrls: ['./form-join.component.scss']
})
export class FormJoinComponent extends BaseFormComponent implements OnInit {
  msgAlert: string;
  classMsgAlert: string;
  constructor(private formBuilder: FormBuilder,
    private companyService: CompanyService,
    private alertMsgEvEmitter: BaseAlertMsgUtils) {
    super();
   }

  ngOnInit() {
    this.formulario = this.formBuilder.group({
      nomeFantasia: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(125)]],
      razaoSocial: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(125)]],
      cnpj: [null, Validators.required],
      adress: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(125)]],
      number: [null, Validators.required],
      bairro: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(125)]],
      fone: [null, Validators.required],
      responsavel: [null, Validators.required],
      cep: [null, Validators.required],
      lat: [null],
     long: [null],
      email: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(125)]],
      cityName: [null, Validators.required],
      uf: [null, Validators.required]
    });

    this.companyService.hideSpinner();
    this.alertMsgEvEmitter.alertMsgEmitt.emit(false);
  }


  submit() {
     this.companyService.join(new Company(this.formulario.get('razaoSocial').value,
     this.formulario.get('nomeFantasia').value, this.formulario.get('uf').value,
     this.formulario.get('adress').value,
     this.formulario.get('number').value,
     this.formulario.get('bairro').value,
     this.formulario.get('fone').value,
     this.formulario.get('cep').value,
     this.formulario.get('responsavel').value,
     this.formulario.get('email').value,
     this.formulario.get('cnpj').value,
     0, 0), this.formulario.get('cityName').value).subscribe(p => {
       if ('S;' === p['ret'] ) {
         this.companyService.hideSpinner();
         this.resetaDadosForm();
         this.showAlertMsg('Cadastro realizado com Sucesso! :)', 'alert-success');
       } else {
         this.showAlertMsg('Falha ao realizar o cadastro!', 'alert-danger');
       }
     });
  }

  showAlertMsg(body: string, classAlert: string) {
    this.alertMsgEvEmitter.alertBodyMsgEmitt.emit(body);
    this.alertMsgEvEmitter.alertClassCssMsgEmitt.emit(classAlert);
    this.alertMsgEvEmitter.alertMsgEmitt.emit(true);
  }

  queryAdress() {
    if (null != this.formulario.get('cep').value) {
      this.companyService.getAdress(this.formulario.get('cep').value).subscribe(p => {
       this.companyService.hideSpinner();
       if (null != p) {
         this.formulario.get('adress').setValue(p.end);
         this.formulario.get('bairro').setValue(p.bairro);
         this.formulario.get('cityName').setValue(p.cidade);
         this.formulario.get('uf').setValue(p.uf);
       }
      });
    }
  }

  queryCnpj() {
      if (null != this.formulario.get('cnpj').value) {
        this.companyService.queryCNPJ(this.formulario.get('cnpj').value).subscribe(p => {
         this.companyService.hideSpinner();
         if (null != p && p === true)  {
          this.showAlertMsg('O CNPJ informado já está cadastrado no sistema!', 'alert-danger');
         }
        });
      }
    }

  queryMail() {
    if (null != this.formulario.get('email').value) {
      this.companyService.queryMail(this.formulario.get('email').value).subscribe(p => {
       this.companyService.hideSpinner();
       if (null != p && p === true)  {
        this.showAlertMsg('O Email informado já está cadastrado no sistema!', 'alert-danger');
       }
      });
    }
  }

  hope() {
    this.companyService.showSpinner();
     this.onSubmit();
  }

  resetaDadosForm() {
     this.formulario.patchValue({
       nomeFantasia: null,
       razaoSocial: null,
       cnpj: null,
       adress: null,
       number: null,
       bairro: null,
       fone: null,
       cep: null,
       email: null,
       responsavel: null,
       cityName: null,
       uf: null
     });
   }

}
