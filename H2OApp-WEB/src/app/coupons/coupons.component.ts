import { BaseAlertMsgUtils } from './../shared/Utils/base-alert-msg-utils';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { ToolbarUtils } from '../shared/Utils/toolbar-utils';
import { IBaseAlertModal } from '../shared/Actions/IBaseAlertModal';
import { TableUtils } from '../shared/Utils/table-utils';
import { CouponsService } from '../shared/services/coupons.service';
import { Coupon } from '../shared/Model/coupon';
declare let $: any;
@Component({
  selector: 'app-coupons',
  templateUrl: './coupons.component.html',
  styleUrls: ['./coupons.component.scss']
})
export class CouponsComponent implements OnInit, OnDestroy, IBaseAlertModal {

  private inscToolBarEmiter: Subscription;
  private inscCouponService: Subscription;
  private inscTablePagEmiter: Subscription;
  private inscSearchTableEmiter: Subscription;
  private idCoupom: number;
  titleModal: string;
  bodyModal: string;
  showModal: string;
  display = 'none';
  coupons: Coupon[];
  first: boolean;
  last: boolean;
  page: number;
  numberOfElements: number;
  size: number;
  sort: any;
  totalElements: number;
  pages: Array<number>;
  limPages: string;
  modalVisible = false;
  isSeacrh: boolean;
  nmSearch: string;
  tbNameCols = ['Cupom', 'Desconto', 'Data Início', 'Data Fim', 'Editar', 'Excluir'];

  constructor(
    private couponService: CouponsService,
    private toolBarEvEmitter: ToolbarUtils,
    private alertMsgEvEmitter: BaseAlertMsgUtils,
    private tabPagEvEmitter: TableUtils
  ) {
  }

  setPage(i) {
    this.page = i;
    if (!this.isSeacrh) {
      this.getAll();
    } else {
      this.search();
    }
  }
  setPageNext() {
    this.page++;
    if (!this.isSeacrh) {
      this.getAll();
    } else {
      this.search();
    }
  }
  setPagePrev() {
    this.page--;
    if (!this.isSeacrh) {
      this.getAll();
    } else {
      this.search();
    }
  }
  setPageLast() {
    this.page = this.pages.length - 1;
    if (!this.isSeacrh) {
      this.getAll();
    } else {
      this.search();
    }
  }
  setPageStart() {
    this.page = 0;
    if (!this.isSeacrh) {
      this.getAll();
    } else {
      this.search();
    }
  }
  clearSearch() {
    this.isSeacrh = false;
    this.nmSearch = null;
    this.page = 0;
    this.getAll();
  }
  changeValue() {
    this.page = 0;
    if (!this.isSeacrh) {
      this.getAll();
    } else {
      this.search();
    }
  }
  clickBtnNo() {
    $('#myModalAlert').modal('hide');
  }
  clickBtnYes() {
    $('#myModalAlert').modal('hide');
    this.inscCouponService = this.couponService
      .del(this.idCoupom)
      .subscribe(
        p => {
          if ('S;' === p['ret']) {
            if (this.page >= 1) {
              this.page--;
            }
            this.showAlertMsg('Exclusão realizada com Sucesso! :)', 'alert-success');
            this.getAll();
          } else {
            this.showAlertMsg('Falha ao excluir!', 'alert-danger');
          }
        },
        error => {
          this.showAlertMsg('Falha ao excluir!', 'alert-danger');
          console.log(error.error.message);
        }
      );
  }
  update(p: Coupon) {
    this.titleModal = 'Editar Cupom';
    this.couponService.update(p);
    $('#myModal').modal('show');
  }
  delete(id) {
    this.titleModal = 'Excluir Cupom';
    this.bodyModal =
      'Atenção: Excluir o cupom, irá apagar todos os dados referentes ao cupom. Deseja continuar?';
    $('#myModalAlert').modal('show');
    this.idCoupom = id;
  }
  getAll() {
    this.couponService.getAll(this.page.toString(), this.limPages).subscribe(
      p => {
        if (null !== p) {
            this.coupons = p['content'];
            this.first = p['first'];
            this.last = p['last'];
            this.page = p['number'];
            this.numberOfElements = p['numberOfElements'];
            this.size = p['size'];
            this.sort = p['sort'];
            this.totalElements = p['totalElements'];
            this.pages = new Array(p['totalPages']);
        }
      },
      error => {
          this.showAlertMsg('Falha ao excluir!', 'alert-danger');
          console.log(error.error.message);
      }
    );
  }
  search() {
      this.couponService.search(this.page.toString(), this.limPages,
      this.nmSearch).subscribe(p => {
        if (null !== p) {
            this.coupons = p['content'];
            this.first = p['first'];
            this.last = p['last'];
            this.page = p['number'];
            this.size = p['size'];
            this.sort = p['sort'];
            this.pages = new Array(p['totalPages']);
        }
      }, (error) => {
        this.showAlertMsg('Falha ao excluir!', 'alert-danger');
        console.log(error.error.message);
    });
  }

  ngOnInit() {
    this.onCloseHandled();
    this.showModal = 'modal fade';
    this.page = 0;
    this.limPages = '5';
    this.nmSearch = null;
    this.getAll();
    this.inscToolBarEmiter = this.toolBarEvEmitter.clicked.subscribe(isOpen => {
      this.onVoted(isOpen);
    });

    this.inscTablePagEmiter = this.tabPagEvEmitter.clickedPag.subscribe(a => {
      switch (a) {
        case -1221:
          this.clearSearch();
          break;
        case -1210:
          this.setPageStart();
          break;
        case -1209:
          this.setPagePrev();
          break;
        case -1208:
          this.setPageNext();
          break;
        case -1207:
          this.setPageLast();
          break;
        case -5:
        case -10:
        case -25:
        case -50:
        case -75:
          this.limPages = (-1 * a).toString();
          this.changeValue();
          break;
        default:
          this.setPage(a);
          break;
      }
    });

    this.inscSearchTableEmiter = this.tabPagEvEmitter.clickedSer.subscribe(a => {
      this.page = 0;
      this.isSeacrh = true;
      this.nmSearch = a;
      this.search();
    });

    this.inscCouponService = this.couponService.couponRefreshEmitter.subscribe(
      p => {
        if (true === p) {
          this.getAll();
        }
      }
    );
    this.alertMsgEvEmitter.alertMsgEmitt.emit(false);
  }

  onCloseHandled() {
    this.display = 'none';
  }

  openModal() {
    this.display = 'block';
  }

  ngOnDestroy() {
    this.inscToolBarEmiter.unsubscribe();
    this.inscCouponService.unsubscribe();
    this.inscTablePagEmiter.unsubscribe();
    this.inscSearchTableEmiter.unsubscribe();
  }
  showAlertMsg(body: string, classAlert: string) {
    this.alertMsgEvEmitter.alertBodyMsgEmitt.emit(body);
    this.alertMsgEvEmitter.alertClassCssMsgEmitt.emit(classAlert);
    this.alertMsgEvEmitter.alertMsgEmitt.emit(true);
  }
  onVoted(b: string) {
    this.titleModal = 'Adicionar Novo Cupom';
    this.couponService.emittNew();
    $('#myModal').modal('show');
  }

  onClickedClose() {
    $('#myModal').modal('hide');
  }


}
