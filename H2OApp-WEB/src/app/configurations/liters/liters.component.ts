import { BaseAlertMsgUtils } from './../../shared/Utils/base-alert-msg-utils';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { Liter } from './../../shared/Model/liter';
import { LiterService } from './../../shared/services/liter.service';
import { ToolbarUtils } from './../../shared/Utils/toolbar-utils';
import { TableUtils } from './../../shared/Utils/table-utils';
import { IBaseAlertModal } from './../../shared/Actions/IBaseAlertModal';
declare let $: any;

@Component({
  selector: 'app-liters',
  templateUrl: './liters.component.html',
  styleUrls: ['./liters.component.scss']
})
export class LitersComponent implements OnInit, OnDestroy, IBaseAlertModal {
  private inscToolBarEmiter: Subscription;
  private inscdeliveryService: Subscription;
  private inscTablePagEmiter: Subscription;
  private id: number;
  titleModal: string;
  bodyModal: string;
  showModal: string;
  display = 'none';
  prods: Liter[];
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
  tbNameCols = [ 'Nome', 'Excluir'];

  constructor(
    private literService: LiterService,
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
    $('#myLitersModalAlert').modal('hide');
  }
  clickBtnYes() {
    $('#myLitersModalAlert').modal('hide');
    this.inscdeliveryService = this.literService
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
  delete(id) {
    this.titleModal = 'Excluir Litro';
    this.bodyModal =
      'Atenção: Excluir o Litro, irá apagar todos os dados referentes a ele. Deseja continuar?';
    $('#myLitersModalAlert').modal('show');
    this.id = id;
  }
  getAll() {
    this.literService.getLitersCompany(this.page.toString(), this.limPages).subscribe(
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
            this.literService.emittLitersCompany(this.prods);
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

    this.inscdeliveryService = this.literService.literRefreshEmitter.subscribe(
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
    if ('L' === b) {
      this.titleModal = 'Adicionar Novo';
      $('#modal-form-liter').modal('show');
    }
  }

  onClickedClose() {
    $('#modal-form-liter').modal('hide');
  }

  showAlertMsg(body: string, classAlert: string) {
    this.alertMsgEvEmitter.alertBodyMsgEmitt.emit(body);
    this.alertMsgEvEmitter.alertClassCssMsgEmitt.emit(classAlert);
    this.alertMsgEvEmitter.alertMsgEmitt.emit(true);
  }


}
