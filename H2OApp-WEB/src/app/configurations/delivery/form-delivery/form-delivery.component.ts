import { BaseAlertMsgUtils } from './../../../shared/Utils/base-alert-msg-utils';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';

import { BaseFormComponent } from './../../../shared/Components/base-form/base-form.component';
import { Delivery } from './../../../shared/Model/delivery';
import { Utils } from './../../../shared/Utils/Utils';
import { DeliveryService } from './../../../shared/services/delivery.service';
@Component({
  selector: 'app-form-delivery',
  templateUrl: './form-delivery.component.html',
  styleUrls: ['./form-delivery.component.scss']
})
export class FormDeliveryComponent extends BaseFormComponent implements OnInit, OnDestroy {
  private inscDeliveryEmiter: Subscription;
  private isUpdate: boolean;
  private idDelivery: number;
  constructor(
    private formBuilder: FormBuilder,
    private deliveryService: DeliveryService,
    private alertMsgEvEmitter: BaseAlertMsgUtils,
    private util: Utils) {
    super();
   }


ngOnInit() {
    this.formulario = this.formBuilder.group({
      distancia: [null, [Validators.required, Validators.pattern('0,00')]],
      valor: [null, [Validators.required, Validators.pattern('0,00')]]
    });

    this.inscDeliveryEmiter = this.deliveryService.deliveryEventEmitter.subscribe(p => {
      if (null !== p) {
        this.isUpdate = true;
        this.idDelivery = p.id;
        this.populaDadosForm(p);
      } else {
        this.isUpdate = false;
        this.idDelivery = 0;
        this.resetaDadosForm();
      }
    });
    this.alertMsgEvEmitter.alertMsgEmitt.emit(false);
  }

  ngOnDestroy(): void {
    this.inscDeliveryEmiter.unsubscribe();
  }

  submit() {
    if (this.isUpdate) {
      this.deliveryService.upd(
          new Delivery(
            this.idDelivery, 0 ,
            this.formulario.get('distancia').value,
            this.util.formatStringToMoney(this.formulario.get('valor').value)
            )
          )
        .subscribe(p => {
          if ('S;' === p['ret']) {
            this.resetaDadosForm();
            this.showAlertMsg('Cadastro realizado com Sucesso! :)', 'alert-success');
            this.deliveryService.refreshTable();
          } else if (null === p['ret'] || 'N;' === p['ret']) {
            this.showAlertMsg('Falha ao realizar o cadastro!', 'alert-danger');
          }
        });
    } else {
      this.deliveryService
        .add(
          new Delivery(
            0,
            0,
            this.formulario.get('distancia').value,
            this.util.formatStringToMoney(this.formulario.get('valor').value)
         )
        )
        .subscribe(p => {
          if ('S;' === p['ret']) {
            this.resetaDadosForm();
            this.showAlertMsg('Cadastro realizado com Sucesso! :)', 'alert-success');
            this.deliveryService.refreshTable();
          } else if (null === p['ret'] || 'N;' === p['ret']) {
            this.showAlertMsg('Falha ao realizar o cadastro!', 'alert-danger');
          }
        });
    }
  }

  showAlertMsg(body: string, classAlert: string) {
    this.alertMsgEvEmitter.alertBodyMsgEmitt.emit(body);
    this.alertMsgEvEmitter.alertClassCssMsgEmitt.emit(classAlert);
    this.alertMsgEvEmitter.alertMsgEmitt.emit(true);
  }

  populaDadosForm(p) {
    this.formulario.patchValue({
      distancia: p.distance,
      valor: this.util.formatNumberToMoney(p.txDelivery)
    });
  }

  resetaDadosForm() {
    this.formulario.patchValue({
      distancia: null,
      valor: null
    });
  }
}
