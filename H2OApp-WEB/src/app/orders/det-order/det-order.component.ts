import { Brand } from './../../shared/Model/brand';
import { BrandService } from './../../shared/services/brand.service';
import { BaseAlertMsgUtils } from './../../shared/Utils/base-alert-msg-utils';

import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription} from 'rxjs';
import { FormBuilder, Validators } from '@angular/forms';

import { BaseFormComponent } from './../../shared/Components/base-form/base-form.component';
import { Utils } from './../../shared/Utils/Utils';
import { OrdersService } from './../../shared/services/orders.service';
import { OrderProduct } from '../../shared/Model/orderProduct';
import { Status } from './../..//shared/Model/status';
import { OrderTable } from './../../shared/Model/orderTable';
@Component({
  selector: 'app-det-order',
  templateUrl: './det-order.component.html',
  styleUrls: ['./det-order.component.scss']
})
export class DetOrderComponent extends BaseFormComponent
implements OnInit, OnDestroy  {
  private inscOrderEmiter: Subscription;
  private inscBrandService: Subscription;
  private idOrder: number;
  brands: Brand[];
  lstProd: OrderProduct[];
  status: any[];
  tbNameCols = ['Litros', 'Marca' , 'Quant.', 'Preço'];
  constructor(
    private formBuilder: FormBuilder,
    private orderService: OrdersService,
    private alertMsgEvEmitter: BaseAlertMsgUtils,
    private brandService: BrandService,
    private util: Utils
  ) {
    super();
  }

  populaDadosForm(p) {
      let numCasa = null;
      if (null != p.numberHouse ) {
           numCasa = ', ' + p.numberHouse;
       }
       this.lstProd = p.lstProduct;
       this.formulario.patchValue({
        cliente: p.name ,
        telefone: p.fone,
        horaPedido: this.util.formatTime(p.dp),
        dataPedido: this.util.formatDate(p.dp),
        formaPagto: this.util.formatPayment(p.formaPagamento),
        dicasEntrega: p.dicasEntrega,
        cupomDesc: p.cupomDesconto,
        taxaEntrega: 'R$' + p.tx,
        desconto: 'R$' + p.valorDesconto,
        troco: p.troco,
        valorTotal: 'R$' + p.vt,
        status: p.stn,
        endereco: {
           rua: p.adress + numCasa ,
           compl: p.complement,
           bairro: p.bairro,
           cidade: p.city // ,
           // estado: p.dtf
        }
      });

   }

  ngOnInit() {
    this.formulario = this.formBuilder.group({
      cliente: [null],
      telefone: [null],
      horaPedido: [null],
      dataPedido: [null],
      endereco: this.formBuilder.group({
        compl: [null],
        rua: [null],
        bairro: [null],
        cidade: [null]
      }),
      formaPagto: [null],
      dicasEntrega: [null],
      cupomDesc: [null],
      taxaEntrega: [null],
      desconto: [null],
      troco: [null],
      valorTotal: [null],
      status: [null]
    });
    this.inscOrderEmiter = this.orderService.couponEventEmitter.subscribe(p => {
      if (null !== p) {
        this.idOrder = p;
      //  this.getBrands();
        this.getOrder();
      } else {
        this.idOrder = 0;
      }
    });

    this.inscBrandService = this.brandService.brandEventEmitter.subscribe(p => {
      this.brands = p;
   });
    this.alertMsgEvEmitter.alertMsgEmitt.emit(false);
    this.getStatus();
  }
  ngOnDestroy(): void {
    this.inscBrandService.unsubscribe();
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
           // this.formulario.get('dtInicio').value,
           null,
           null,
           idM.id.toString()
           // this.formulario.get('dtFim').value
          )
        )
        .subscribe(p => {
          if ('S;' === p['ret']) {
            this.showAlertMsg('Cadastro realizado com Sucesso! :)', 'alert-success');
            this.resetaDadosForm();
            this.orderService.refreshTable();
          } else if (null === p['ret'] || 'N;' === p['ret']) {
            this.showAlertMsg('Falha ao realizar o cadastro!', 'alert-danger');
          }
        });
  }
  getBrandById(id: number) {
   /* if (id !== undefined) {
      alert('AR');
      return this.brandService.getNameBrand(id);
    }*/
    if (this.brands !== undefined && id !== undefined) {
      for (let i = 0; i < this.brands.length; i++) {
       if (this.brands[i].id === id) {
          const b = this.brands[i];
          i = this.brands.length;
          return b.nm;
       }
      }
    }
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
  getOrder() {
    this.orderService.getOrder(this.idOrder).subscribe(
      p => {
        this.populaDadosForm(p);
      },
      error => {
        this.showAlertMsg('Falha ao pegar os dados!', 'alert-danger');
        console.log(error.error.message);
      }
    );
  }
 /* getBrands() {
    this.inscBrandService = this.brandService.getBrandsLocal().subscribe(b => {
      this.brands = b;
     // this.brandService.getBrandsLocal2(this.brands);
    });
  }*/
  showAlertMsg(body: string, classAlert: string) {
    this.alertMsgEvEmitter.alertBodyMsgEmitt.emit(body);
    this.alertMsgEvEmitter.alertClassCssMsgEmitt.emit(classAlert);
    this.alertMsgEvEmitter.alertMsgEmitt.emit(true);
  }
}
