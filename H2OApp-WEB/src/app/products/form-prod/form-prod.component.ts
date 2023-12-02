import { BaseAlertMsgUtils } from './../../shared/Utils/base-alert-msg-utils';
import { FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit, OnDestroy, AfterViewInit } from '@angular/core';
import { Subscription, Observable } from 'rxjs';

import { BaseFormComponent } from './../../shared/Components/base-form/base-form.component';
import { ProductService } from './../../shared/services/product.service';
import { FormValidations } from './../../shared/Utils/form-validations';
import { BrandService } from './../../shared/services/brand.service';
import { Utils } from './../../shared/Utils/Utils';
import { Brand } from './../../shared/Model/brand';
import { Product } from './../../shared/Model/product';
@Component({
  selector: 'app-form-prod',
  templateUrl: './form-prod.component.html',
  styleUrls: ['./form-prod.component.scss']
})
export class FormProdComponent extends BaseFormComponent implements OnInit, OnDestroy, AfterViewInit {
  private inscProductEmiter: Subscription;
  private inscBrandService: Subscription;
  brands2: Observable<Brand[]>;
  isUpdate: boolean;
  idProd: number;
  brands: Brand[];
  constructor(
    private formBuilder: FormBuilder,
    private prodService: ProductService,
    private alertMsgEvEmitter: BaseAlertMsgUtils,
    private brandService: BrandService,
    private util: Utils) {
    super();
   }

  ngOnInit() {
    this.formulario = this.formBuilder.group({
      nome: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(125)]],
      valor: [null, Validators.pattern('0,00')],
      litros: [null, Validators.pattern('0,00')],
      marcas: [null]

    });
    this.inscProductEmiter = this.prodService.prodEventEmitter.subscribe(p => {
      if  (null !== p) {
        this.isUpdate = true;
        this.idProd = p.idP;
        this.populaDadosForm(p);
      } else {
        this.isUpdate = false;
        this.idProd = 0;
        this.resetaDadosForm();
      }
     });
     this.inscBrandService = this.brandService.brandEventEmitter.subscribe(p => {
        this.brands = p;
     });
     this.alertMsgEvEmitter.alertMsgEmitt.emit(false);
  }
  ngOnDestroy() {
    this.inscProductEmiter.unsubscribe();
    this.inscBrandService.unsubscribe();
  }

  ngAfterViewInit() { }

  buildFrameworks() { }

  submit() {
     const idM: Brand = this.formulario.get('marcas').value;
     let valor: number = this.formulario.get('valor').value;
     const valor2 = valor.toLocaleString('USD').replace('.', '').replace(',', '.');
     valor = Number.parseFloat(valor2);
     if (this.isUpdate) {
      this.prodService.updProduct(new Product(this.idProd, this.formulario.get('nome').value,
      this.formulario.get('litros').value,
      valor, idM.id)).subscribe(p => {
        if ('S;' === p['ret'] ) {
          this.showAlertMsg('Cadastro realizado com Sucesso! :)', 'alert-success');
          this.resetaDadosForm();
          this.prodService.refreshProductTable();
        }  else {
          this.showAlertMsg('Falha ao realizar o cadastro!', 'alert-danger');
         }
      });
     } else {
      this.prodService.addProduct(new Product(this.idProd, this.formulario.get('nome').value,
      this.formulario.get('litros').value,
      valor, idM.id)).subscribe(p => {
        if ('S;' === p['ret'] ) {
          this.showAlertMsg('Cadastro realizado com Sucesso! :)', 'alert-success');
          this.resetaDadosForm();
          this.prodService.refreshProductTable();
        } else {
          this.showAlertMsg('Falha ao realizar o cadastro!', 'alert-danger');
         }
      });
     }
 }
  populaDadosForm(p) {
    this.formulario.patchValue({
      nome: p.nm,
      valor: this.util.formatNumberToMoney(p.vl),
      litros: p.lt,
      marcas:  this.getBrandById(p.idB)
    });
  }
  getBrandById(id: number) {
    if (this.brands !== undefined && id !== undefined) {
      for (let i = 0; i < this.brands.length; i++) {
       if (this.brands[i].id === id) {
          const b = this.brands[i];
          i = this.brands.length;
          return b;
       }
      }
    }
  }
  resetaDadosForm() {
    this.formulario.patchValue({
      nome: null,
      valor: null,
      litros: null,
      marcas: null
    });
  }
  compararCargos(obj1, obj2) {
    return obj1 && obj2 ? (obj1.nome === obj2.nome && obj1.nivel === obj2.nivel) : obj1 === obj2;
  }
  getBrands() { }

  showAlertMsg(body: string, classAlert: string) {
    this.alertMsgEvEmitter.alertBodyMsgEmitt.emit(body);
    this.alertMsgEvEmitter.alertClassCssMsgEmitt.emit(classAlert);
    this.alertMsgEvEmitter.alertMsgEmitt.emit(true);
  }
}
