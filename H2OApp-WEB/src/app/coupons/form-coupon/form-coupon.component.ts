import { BaseAlertMsgUtils } from './../../shared/Utils/base-alert-msg-utils';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription} from 'rxjs';
import { FormBuilder, Validators } from '@angular/forms';

import { BaseFormComponent } from './../../shared/Components/base-form/base-form.component';
import { CouponsService } from './../../shared/services/coupons.service';
import { Utils } from './../../shared/Utils/Utils';
import { Coupon } from './../../shared/Model/coupon';
@Component({
  selector: 'app-form-coupon',
  templateUrl: './form-coupon.component.html',
  styleUrls: ['./form-coupon.component.scss']
})
export class FormCouponComponent extends BaseFormComponent
implements OnInit, OnDestroy {
  private inscCouponEmiter: Subscription;
  private isUpdate: boolean;
  private idCoupon: number;
  constructor(
    private formBuilder: FormBuilder,
    private couponService: CouponsService,
    private alertMsgEvEmitter: BaseAlertMsgUtils,
    private util: Utils
  ) {
    super();
  }


  ngOnInit() {
    this.formulario = this.formBuilder.group({
      cupom: [
        null,
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(125)
        ]
      ],
      valor: [
        null,
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(125)
        ]
      ],
      hrInicio: [
        '00:00:00',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(8)
        ]
      ],
      dtInicio: [
        null,
        [
          Validators.required,
          Validators.minLength(10),
          Validators.maxLength(10)
        ]
      ],
      hrFim: [
        '00:00:00',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(8)
        ]
      ],
      dtFim: [
        null,
        [
          Validators.required,
          Validators.minLength(10),
          Validators.maxLength(10)
        ]
      ]
    });

    this.inscCouponEmiter = this.couponService.couponEventEmitter.subscribe(p => {
      if (null !== p) {
        this.isUpdate = true;
        this.idCoupon = p.idC;
        this.populaDadosForm(p);
      } else {
        this.isUpdate = false;
        this.idCoupon = 0;
        this.resetaDadosForm();
      }
    });
    this.alertMsgEvEmitter.alertMsgEmitt.emit(false);
  }
  ngOnDestroy(): void {
    this.inscCouponEmiter.unsubscribe();
  }

  submit() {
    const dtI = this.util.formatDateTime(this.formulario.get('hrInicio').value, this.formulario.get('dtInicio').value);
    const dtF = this.util.formatDateTime(this.formulario.get('hrFim').value, this.formulario.get('dtFim').value);
    if (this.isUpdate) {
      this.couponService
        .upd(
          new Coupon(
            this.idCoupon,
            this.formulario.get('cupom').value,
            dtI,
            dtF,
            this.util.formatRs(this.formulario.get('valor').value)
          )
        )
        .subscribe(p => {
          if ('S;' === p['ret']) {
            this.showAlertMsg('Cadastro realizado com Sucesso! :)', 'alert-success');
            this.resetaDadosForm();
            this.couponService.refreshTable();
          } else if (null === p['ret'] || 'N;' === p['ret']) {
            this.showAlertMsg('Falha ao realizar o cadastro!', 'alert-danger');
          }
        });
    } else {
      this.couponService
        .add(
          new Coupon(
            this.idCoupon,
            this.formulario.get('cupom').value,
            dtI,
            dtF,
            this.util.formatRs(this.formulario.get('valor').value)
          )
        )
        .subscribe(p => {
          if ('S;' === p['ret']) {
            this.showAlertMsg('Cadastro realizado com Sucesso! :)', 'alert-success');
            this.resetaDadosForm();
            this.couponService.refreshTable();
          } else if (null === p['ret'] || 'N;' === p['ret']) {
            this.showAlertMsg('Falha ao realizar o cadastro!', 'alert-danger');
          }
        });
    }
  }

  populaDadosForm(p) {
    this.formulario.patchValue({
      cupom: p.vc,
      valor: this.util.formatRs(p.vd),
      dtInicio: this.util.formatDate(p.dti),
      dtFim: this.util.formatDate(p.dtf),
      hrInicio: this.util.formatTime(p.dti),
      hrFim: this.util.formatTime(p.dtf)
    });
  }
  resetaDadosForm() {
    this.formulario.patchValue({
      cupom: null,
      valor: null,
      dtInicio: null,
      dtFim: null,
      hrInicio: '00:00:00',
      hrFim: '00:00:00'
    });
  }

  showAlertMsg(body: string, classAlert: string) {
    this.alertMsgEvEmitter.alertBodyMsgEmitt.emit(body);
    this.alertMsgEvEmitter.alertClassCssMsgEmitt.emit(classAlert);
    this.alertMsgEvEmitter.alertMsgEmitt.emit(true);
  }

}
