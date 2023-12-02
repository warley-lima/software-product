import { BaseAlertMsgUtils } from './../../../shared/Utils/base-alert-msg-utils';
import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs';

import { Utils } from './../../../shared/Utils/Utils';
import { Liter } from './../../../shared/Model/liter';
import { LiterService } from './../../../shared/services/liter.service';

@Component({
  selector: 'app-form-liters',
  templateUrl: './form-liters.component.html',
  styleUrls: ['./form-liters.component.scss']
})
export class FormLitersComponent implements OnInit, OnDestroy {


  private inscBrandsCompany: Subscription;
  private idsBrandsSelected: number[];
  private idsBrandsSelectedServer: number[];
  @Input() litersCompany: Liter[];
  liters: Liter[];
  private inscBrandService: Subscription;
  constructor(
    private literService: LiterService,
    private alertMsgEvEmitter: BaseAlertMsgUtils,
    private util: Utils) {
   }


  ngOnInit() {
    this.idsBrandsSelected = [];
    this.inscBrandsCompany = this.literService.literCompanyEventEmitter.subscribe(p => {
        this.litersCompany = p;
        this.getAllLiters();
    });
    this.inscBrandService = this.literService.literRefreshEmitter.subscribe(
      p => {
        if (true === p) {
          this.getAllLiters();
        }
      }
    );
    this.alertMsgEvEmitter.alertMsgEmitt.emit(false);
  }

  getLitersCompany() {
    this.idsBrandsSelected = [];
  }

 getAllLiters() {
      this.inscBrandService = this.literService.getLiters().subscribe(b => {
        this.liters = b;
        this. getLitersCompanySelected();
      });
 }

 getLitersCompanySelected() {
  this.inscBrandService = this.literService.getLitersCompanySelected().subscribe(b => {
    if (null !== b) {
      this.idsBrandsSelectedServer = b;
      this.clearBrandsCompany();
    }
  });
}

  clearBrandsCompany() {
    if (null != this.idsBrandsSelectedServer && null != this.liters) {
      this.idsBrandsSelectedServer.forEach(b => {
       for (let i = 0; i < this.liters.length; i++) {
         if (b === this.liters[i].idL) {
           this.liters.splice(i, 1);
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
   this.literService
   .add(
     this.idsBrandsSelected
    ).subscribe(p => {
     if ('S;' === p['ret']) {
      this.showAlertMsg('Cadastro realizado com Sucesso! :)', 'alert-success');
      this.getLitersCompany();
      this.literService.refreshTable();
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
