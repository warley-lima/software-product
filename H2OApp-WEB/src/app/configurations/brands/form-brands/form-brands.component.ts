import { BaseAlertMsgUtils } from './../../../shared/Utils/base-alert-msg-utils';

import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs';

import { Utils } from './../../../shared/Utils/Utils';
import { BrandService } from './../../../shared/services/brand.service';
import { Brand } from './../../../shared/Model/brand';
@Component({
  selector: 'app-form-brands',
  templateUrl: './form-brands.component.html',
  styleUrls: ['./form-brands.component.scss']
})
export class FormBrandsComponent implements OnInit, OnDestroy {
  private inscBrandsCompany: Subscription;
  private idsBrandsSelected: number[];
  private idsBrandsSelectedServer: number[];
  @Input() brandsCompany: Brand[];
  brands: Brand[];
  private inscBrandService: Subscription;
  constructor(
    private brandService: BrandService,
    private alertMsgEvEmitter: BaseAlertMsgUtils,
    private util: Utils) {
   }


  ngOnInit() {
    this.idsBrandsSelected = [];
    this.inscBrandsCompany = this.brandService.brandCompanyEventEmitter.subscribe(p => {
        this.brandsCompany = p;
        this.getAllBrands();
    });
    this.inscBrandService = this.brandService.brandsRefreshEmitter.subscribe(
      p => {
        if (true === p) {
          this.getAllBrands();
        }
      }
    );
    this.alertMsgEvEmitter.alertMsgEmitt.emit(false);
  }

  getBrandsCompany() {
    this.idsBrandsSelected = [];
  }

 getAllBrands() {
      this.inscBrandService = this.brandService.getBrands().subscribe(b => {
        this.brands = b;
        this. getBrandsCompanySelected();
      });
 }

 getBrandsCompanySelected() {
  this.inscBrandService = this.brandService.getBrandsCompanySelected().subscribe(b => {
    if (null !== b) {
      this.idsBrandsSelectedServer = b;
      this.clearBrandsCompany();
    }

  });
}

  clearBrandsCompany() {
    if (null != this.idsBrandsSelectedServer && null != this.brands) {
      this.idsBrandsSelectedServer.forEach(b => {
       for (let i = 0; i < this.brands.length; i++) {
         if (b === this.brands[i].id) {
          this.brands.splice(i, 1);
       }

       }
     });
    }
  }

  ngOnDestroy(): void {
    this.inscBrandService.unsubscribe();
    this.inscBrandsCompany.unsubscribe();
  }

  clickItem(idBrand: number) {
    if (-1 === this.idsBrandsSelected.indexOf(idBrand, 0)) {
        this.idsBrandsSelected.push(idBrand);
    } else {
      const pos = this.idsBrandsSelected.indexOf(idBrand, 0);
      this.idsBrandsSelected.splice(pos, 1);
    }
  }

  submit() {
    this.brandService.add(this.idsBrandsSelected).subscribe(p => {
     if ('S;' === p['ret']) {
        this.getBrandsCompany();
        this.showAlertMsg('Cadastro realizado com Sucesso!)', 'alert-success');
        this.brandService.refreshTable();
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

}
