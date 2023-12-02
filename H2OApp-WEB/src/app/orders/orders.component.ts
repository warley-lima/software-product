import { Brand } from './../shared/Model/brand';
import { BrandService } from './../shared/services/brand.service';
import { BaseAlertMsgUtils } from './../shared/Utils/base-alert-msg-utils';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { IBaseAlertModal } from '../shared/Actions/IBaseAlertModal';
import { TableUtils } from '../shared/Utils/table-utils';
declare let $: any;
import { OrdersService } from '../shared/services/orders.service';
import { OrderTable } from '../shared/Model/orderTable';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit, OnDestroy, IBaseAlertModal {
  private inscCouponService: Subscription;
  private inscTablePagEmiter: Subscription;
  private inscSearchTableEmiter: Subscription;
  private inscBrandService: Subscription;
  private idCoupom: number;
  titleModal: string;
  bodyModal: string;
  showModal: string;
  display = 'none';
  orders: OrderTable[];
  brands: Brand[];
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
  tbNameCols = ['Id', 'Cliente', 'Telefone', 'Valor', 'Data', 'Status', 'Editar', 'Detalhes'];

  constructor(
    private couponService: OrdersService,
    private alertMsgEvEmitter: BaseAlertMsgUtils,
    private brandService: BrandService,
    private tabPagEvEmitter: TableUtils
  ) {}

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
  }
  update(p: OrderTable) {
    this.titleModal = 'Editar Status Pedido';
    this.couponService.update(p.idO);
    $('#myModal').modal('show');
  }
  info(id) {
    this.titleModal = 'Detalhes do Pedido';
   // this.bodyModal =
   //   'Atenção: Excluir o cupom, irá apagar todos os dados referentes ao cupom. Deseja continuar?';
    $('#myModal2').modal('show');
    this.idCoupom = id;
    this.brandService.brandEventEmitter.emit(this.brands);
    this.couponService.update(id);
  }
  getAll() {
    this.couponService.getAll(this.page.toString(), this.limPages).subscribe(
      p => {
       if (null !== p) {
        this.orders = p['content'];
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
         this.showAlertMsg('Falha ao pegar os dados!', 'alert-danger');
         console.log(error.error.message);
      }
    );
  }
  search() {
      this.couponService.search(this.page.toString(), this.limPages,
      this.nmSearch).subscribe(p => {
        if (null !== p) {
          this.orders = p['content'];
          this.first = p['first'];
          this.last = p['last'];
          this.page = p['number'];
          this.size = p['size'];
          this.sort = p['sort'];
          this.pages = new Array(p['totalPages']);
        }
      }, (error) => {
        this.showAlertMsg('Falha ao pegar os dados!', 'alert-danger');
        console.log(error.error.message);
    });
  }
  newOrder() {
     this.couponService.connect();
  }

 /* getBrands() {
    this.inscBrandService = this.brandService.getBrandsLocal().subscribe(b => {
      this.brands = b;
     // this.brandService.getBrandsLocal2(this.brands);
    });
  }*/
  getBrands() {
     if (this.brandService.hasBrandsLocal()) {
         this.brands = this.brandService.getBrandsLocal();
         this.brandService.getBrandsLocal2(this.brands);
     } else {
        this.inscBrandService = this.brandService.getAllBrandsByCompany().subscribe(
          b => {
          if (null !== b) {
          this.brands = b;
          this.brandService.getBrandsLocal2(this.brands);
          this.inscBrandService.unsubscribe();
          }
        },
        error => {
          this.showAlertMsg('Falha ao pegar as marcas!', 'alert-danger');
          console.log(error.error.message);
        }
      );
     }
  }
  ngOnInit() {
    this.onCloseHandled();
    this.showModal = 'modal fade';
    this.page = 0;
    this.limPages = '5';
    this.nmSearch = null;
    this.getAll();
    this.getBrands();
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
   // this.inscBrandService.unsubscribe();
    this.inscCouponService.unsubscribe();
    this.inscTablePagEmiter.unsubscribe();
    this.inscSearchTableEmiter.unsubscribe();
  }
  onClickedClose() {
    $('#myModal').modal('hide');
    $('#myModal2').modal('hide');
  }
  showAlertMsg(body: string, classAlert: string) {
    this.alertMsgEvEmitter.alertBodyMsgEmitt.emit(body);
    this.alertMsgEvEmitter.alertClassCssMsgEmitt.emit(classAlert);
    this.alertMsgEvEmitter.alertMsgEmitt.emit(true);
  }
}
