import { BaseAlertMsgUtils } from './../shared/Utils/base-alert-msg-utils';
import { Component, OnInit, OnDestroy } from '@angular/core';
import {FormControl   } from '@angular/forms';
import { Subscription } from 'rxjs';

import { ToolbarUtils } from '../shared/Utils/toolbar-utils';
import { IBaseAlertModal } from '../shared/Actions/IBaseAlertModal';
import { TableUtils } from '../shared/Utils/table-utils';
import { UserService } from '../shared/services/user.service';
import { UserServer } from '../shared/Model/userServer';
declare let $: any;
@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit, OnDestroy, IBaseAlertModal {

  private inscToolBarEmiter: Subscription;
  private inscUserService: Subscription;
  private inscTablePagEmiter: Subscription;
  private inscSearchTableEmiter: Subscription;
  private idUser: number;
  titleModal: string;
  bodyModal: string;
  showModal: string;
  display = 'none';
  users: UserServer[];
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
  tbNameCols = ['Nome', 'Email', 'Editar', 'Excluir'];

  constructor(
    private userService: UserService,
    private toolBarEvEmitter: ToolbarUtils,
    private alertMsgEvEmitter: BaseAlertMsgUtils,
    private tabPagEvEmitter: TableUtils
  ) {
  }

  setPage(i) {
    this.page = i;
    if (!this.isSeacrh) {
      this.getUsers();
    } else {
      this.searchUsers();
    }
  }
  setPageNext() {
    this.page++;
    if (!this.isSeacrh) {
      this.getUsers();
    } else {
      this.searchUsers();
    }
  }
  setPagePrev() {
    this.page--;
    if (!this.isSeacrh) {
      this.getUsers();
    } else {
      this.searchUsers();
    }
  }
  setPageLast() {
    this.page = this.pages.length - 1;
    if (!this.isSeacrh) {
      this.getUsers();
    } else {
      this.searchUsers();
    }
  }
  setPageStart() {
    this.page = 0;
    if (!this.isSeacrh) {
      this.getUsers();
    } else {
      this.searchUsers();
    }
  }
  clearSearch() {
    this.isSeacrh = false;
    this.nmSearch = null;
    this.page = 0;
    this.getUsers();
  }
  changeValue() {
    this.page = 0;
    if (!this.isSeacrh) {
      this.getUsers();
    } else {
      this.searchUsers();
    }
  }
  clickBtnNo() {
    $('#myModalAlert').modal('hide');
  }
  clickBtnYes() {
    $('#myModalAlert').modal('hide');
    this.inscUserService = this.userService
      .delUser(this.idUser)
      .subscribe(
        p => {
          if ('S;' === p['ret']) {
            if (this.page >= 1) {
              this.page--;
            }
            this.showAlertMsg('Exclusão realizada com Sucesso! :)', 'alert-success');
            this.getUsers();
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
  updateUser(p: UserServer) {
    this.titleModal = 'Editar Usuário';
    this.userService.updateUser(p);
    $('#myModal').modal('show');
  }
  deleteUser(id) {
    this.titleModal = 'Excluir Usuário';
    this.bodyModal =
      'Atenção: Excluir o usuário, irá apagar todos os dados referentes ao usuário. Deseja continuar?';
    $('#myModalAlert').modal('show');
    this.idUser = id;
  }
  getUsers() {
    this.userService.getUsers(this.page.toString(), this.limPages).subscribe(
      p => {
        if (null !== p) {
            this.users = p['content'];
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
  searchUsers() {
      this.userService.searchUsers(this.page.toString(), this.limPages,
      this.nmSearch).subscribe(p => {
        if (null !== p) {
            this.users = p['content'];
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

  ngOnInit() {
    this.onCloseHandled();
    this.showModal = 'modal fade';
    this.page = 0;
    this.limPages = '5';
    this.nmSearch = null;
    this.getUsers();
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
      this.searchUsers();
    });

    this.inscUserService = this.userService.userRefreshEmitter.subscribe(
      p => {
        if (true === p) {
          this.getUsers();
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
    this.inscUserService.unsubscribe();
    this.inscTablePagEmiter.unsubscribe();
    this.inscSearchTableEmiter.unsubscribe();
  }
  showAlertMsg(body: string, classAlert: string) {
    this.alertMsgEvEmitter.alertBodyMsgEmitt.emit(body);
    this.alertMsgEvEmitter.alertClassCssMsgEmitt.emit(classAlert);
    this.alertMsgEvEmitter.alertMsgEmitt.emit(true);
  }
  onVoted(b: string) {
    this.titleModal = 'Adicionar Novo Usuário';
    this.userService.emittUser();
    $('#myModal').modal('show');
  }
  onClickedClose() {
    $('#myModal').modal('hide');
  }
  validarEmail(formControl: FormControl) {
    /*return this.verificaEmailService.verificarEmail(formControl.value)
      .pipe(map(emailExiste => emailExiste ? { emailInvalido: true } : null));*/
    return true;
  }

}
