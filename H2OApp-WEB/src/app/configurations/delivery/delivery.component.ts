import { BaseAlertMsgUtils } from './../../shared/Utils/base-alert-msg-utils';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { ToolbarUtils } from './../../shared/Utils/toolbar-utils';
import { TableUtils } from './../../shared/Utils/table-utils';
import { IBaseAlertModal } from './../../shared/Actions/IBaseAlertModal';
import { DeliveryService } from './../../shared/services/delivery.service';
import { Delivery } from './../../shared/Model/delivery';

declare let $: any;
@Component({
  selector: 'app-delivery',
  templateUrl: './delivery.component.html',
  styleUrls: ['./delivery.component.scss']
})
export class DeliveryComponent  implements OnInit, OnDestroy, IBaseAlertModal {
  private inscToolBarEmiter: Subscription;
  private inscdeliveryService: Subscription;
  private inscTablePagEmiter: Subscription;
  private id: number;
  titleModal: string;
  bodyModal: string;
  showModal: string;
  display = 'none';
  prods: Delivery[];
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
  tbNameCols = ['Distância(KM)', 'Valor', 'Editar', 'Excluir'];

  constructor(
    private deliveryService: DeliveryService,
    private toolBarEvEmitter: ToolbarUtils,
    private alertMsgEvEmitter: BaseAlertMsgUtils,
    private tabPagEvEmitter: TableUtils) {

  }


  setPage(i) {
    this.page = i;
    if (!this.isSeacrh) {
      this.getAll();
    }
  }
  setPageNext() {
    this.page++;
    if (!this.isSeacrh) {
      this.getAll();
    }
  }
  setPagePrev() {
    this.page--;
    if (!this.isSeacrh) {
      this.getAll();
    }
  }
  setPageLast() {
    this.page = this.pages.length - 1;
    if (!this.isSeacrh) {
      this.getAll();
    }
  }
  setPageStart() {
    this.page = 0;
    if (!this.isSeacrh) {
      this.getAll();
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
    }
  }
  clickBtnNo() {
    $('#myModalAlert').modal('hide');
  }
  clickBtnYes() {
    $('#myModalAlert').modal('hide');
    this.inscdeliveryService = this.deliveryService
      .del(this.id)
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
  update(p: Delivery) {
    this.titleModal = 'Editar Opção de Entrega';
    this.deliveryService.update(p);
    $('#myModal').modal('show');
  }
  delete(id) {
    this.titleModal = 'Excluir Opção de Entrega';
    this.bodyModal =
      'Atenção: Excluir a Opção de Entrega, irá apagar todos os dados referentes a ela. Deseja continuar?';
    $('#myModalAlert').modal('show');
    this.id = id;
  }
  getAll() {
    this.deliveryService.getAll(this.page.toString(), this.limPages).subscribe(
      p => {
        if (null !== p) {
            this.prods = p['content'];
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
    this.inscdeliveryService = this.deliveryService.deliveryRefreshEmitter.subscribe(
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
    this.inscdeliveryService.unsubscribe();
    this.inscTablePagEmiter.unsubscribe();
  }

  onVoted(b: string) {
    if ('E' === b) {
      this.titleModal = 'Adicionar Opção de Entrega';
      this.deliveryService.emittNew();
      $('#myModal').modal('show');
    }

  }

  onClickedClose() {
    $('#myModal').modal('hide');
  }

  showAlertMsg(body: string, classAlert: string) {
    this.alertMsgEvEmitter.alertBodyMsgEmitt.emit(body);
    this.alertMsgEvEmitter.alertClassCssMsgEmitt.emit(classAlert);
    this.alertMsgEvEmitter.alertMsgEmitt.emit(true);
  }

}
