import { BaseAlertMsgUtils } from './../../shared/Utils/base-alert-msg-utils';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription} from 'rxjs';
import { FormBuilder, Validators } from '@angular/forms';

import { BaseFormComponent } from './../../shared/Components/base-form/base-form.component';
import { Utils } from './../../shared/Utils/Utils';
import { OrdersService } from './../../shared/services/orders.service';
import { OrderTable } from '../../shared/Model/orderTable';
import { Status } from './../..//shared/Model/status';

@Component({
  selector: 'app-form-upd-order',
  templateUrl: './form-upd-order.component.html',
  styleUrls: ['./form-upd-order.component.scss']
})
export class FormUpdOrderComponent extends BaseFormComponent
implements OnInit, OnDestroy  {
  private inscOrderEmiter: Subscription;
  private idOrder: number;
  status: any[];

  constructor(
    private formBuilder: FormBuilder,
    private orderService: OrdersService,
    private alertMsgEvEmitter: BaseAlertMsgUtils,
    private util: Utils
  ) { super(); }
  ngOnInit() {
    this.formulario = this.formBuilder.group({
      statusSel: [
        null,
        [
          Validators.required
        ]
      ]
    });
    this.inscOrderEmiter = this.orderService.couponEventEmitter.subscribe(p => {
      if (null !== p) {
        this.idOrder = p; // p.idO;
      } else {
      //  this.isUpdate = false;
        this.idOrder = 0;
      }
    });
    this.alertMsgEvEmitter.alertMsgEmitt.emit(false);
    this.getStatus();

  }
  ngOnDestroy(): void {
    this.inscOrderEmiter.unsubscribe();
  }
  submit() {
    const idM: Status = this.formulario.get('statusSel').value;
    this.orderService
        .upd(
          new OrderTable(
            this.idOrder,
            null,
            null,
           null,
           null,
           idM.id.toString()
          )
        )
        .subscribe(p => {
          if ('S;' === p['ret']) {
            this.showAlertMsg('Cadastro realizado com Sucesso! :)', 'alert-success');
            this.resetaDadosForm();
            this.orderService.refreshTable();
          }  else if (null === p['ret'] || 'N;' === p['ret']) {
            this.showAlertMsg('Falha ao realizar o cadastro!', 'alert-danger');
          }
        });
  }
  populaDadosForm(p) {
  }
  resetaDadosForm() {
  this.formulario.patchValue({
      statusSel: null
    });
  }
  getStatus() {
     this.status = [
       new Status(2, 'Entregue' ),
       new Status(3, 'Enviado' ),
       new Status(4, 'Cancelado' ),
       new Status(5, 'Devolvido' )

    ];
  }
  showAlertMsg(body: string, classAlert: string) {
    this.alertMsgEvEmitter.alertBodyMsgEmitt.emit(body);
    this.alertMsgEvEmitter.alertClassCssMsgEmitt.emit(classAlert);
    this.alertMsgEvEmitter.alertMsgEmitt.emit(true);
  }

}
