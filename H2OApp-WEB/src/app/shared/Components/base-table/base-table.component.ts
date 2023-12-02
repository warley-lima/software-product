// import { IBaseTable } from './../../Actions/IBaseTable';
import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { TableUtils } from '../../Utils/table-utils';

@Component({
  selector: 'app-base-table',
  template: `
  <div class="table-responsive">
        <form [formGroup]="formTable" *ngIf="showSearchBar">
            <div class="form-group row mr-md-0 mb-2 mt-2">
                <label class="col-auto col-form-label font-weight-bold" style="margin-left: -0.07rem;" for="nmSearch">Buscar:</label>
                <input type="text" class="form-control mr-1 inptSearch"  id="nmSearch"  formControlName="nmSearch" >
                <button type="button" class="btn btn-primary mr-1" (click)="searchProd()">
					Ir</button>
			      	<button type="button" class="btn btn-info btnSearch" (click)="clearSearch()">
					Limpar</button>
            </div>
        </form>

        <table class="table table-hover">
            <thead>
			          <tr>
                    <th scope="col" colspan="2" *ngFor="let col of tbCols">{{ col }}</th>
                </tr>
            </thead>
            <ng-content></ng-content>
        </table>
   </div>
   <div class="col-12 align-items-center">
      <nav>
          <ul class="pagination pagination-sm justify-content-center mb-2 mt-2" style="color: #007bff;">
            <li class="page-item" *ngIf="!first" (click)="setPageStart()">
              <a class="page-link">Início</a>
            </li>
            <li class="page-item" *ngIf="!first" (click)="setPagePrev()">
              <a class="page-link">Anterior</a>
            </li>
            <li class="page-item" *ngFor="let p of pages; let i = index" [ngClass]="{ active: i == page }">
              <a
                class="page-link"
                (click)="setPage(i)"
                >{{ i + 1 }}</a
              >
            </li>
            <li class="page-item" *ngIf="!last" (click)="setPageNext()">
              <a class="page-link">Próxima</a>
            </li>
            <li class="page-item" *ngIf="!last" (click)="setPageLast()">
              <a class="page-link">Fim</a>
            </li>
          </ul>
      </nav>
    </div>
    <div class="col-12 align-items-center">
      <div class="pagination-sm pagination justify-content-center  mb-2 mt-2">
        <form [formGroup]="formTable" >
          <label for="itemsOnPageSelect2">Mostrar:</label>
          <select
            id="itemsOnPage"
            formControlName="itemsPerPage"
            class="custom-select selectMy"
            (change)= changeValue()
          >
              <option *ngFor="let item of itemsPerPage2"  [ngValue]="item">{{ item }}</option>
          </select>
        </form>
      </div>
    </div>
  `,
  styles: [
    `@media ( max-width : 23.44em) {
      .inptSearch{
        margin-left: -0.75rem; width: 9.38rem;
      }
      .btnSearch{
        width: 3.35rem; text-align: left; padding: 0rem
      }
     }
     @media (min-width : 23.45em) and (max-width : 39.99em){
      .inptSearch{
        margin-left: -0.75rem; width: 12.5rem;
      }
      .btnSearch{
        width: 3.35rem; text-align: left; padding: 0rem
      }
     }
     @media (min-width : 40em) {
      .inptSearch{
        width: 18.75rem;
      }
     }
     .selectMy{
      width: 5rem;
      height: 1.75rem;
      margin-left: 0.5rem;
      margin-right: 0.5rem;
      padding: 0rem 0.75rem 0.375rem 0.95rem !important;
    }
    `
  ]
})
export class BaseTableComponent  implements OnInit {
  formTable: FormGroup;
  itemsPerPage2: any[];
  @Input() tbCols: any[];
  @Input() pages: Array<number>;
  @Input() showSearchBar: boolean;
  @Input() first: boolean;
  @Input() last: boolean;
  @Input() page: number;
  constructor(
    private formBuilder2: FormBuilder,
    private paginationEvEmitter: TableUtils) {
  }

  ngOnInit() {
    this.createTable();
  }
  createTable() {
    this.formTable = this.formBuilder2.group({
     nmSearch: [null, [Validators.minLength(3), Validators.maxLength(125)]],
     itemsPerPage: [5]
    });
    this.itemsPerPage2 = [5, 10, 25, 50, 75];
  }
  searchProd() {
    this.paginationEvEmitter.clickSearchTableEmitter(this.formTable.get('nmSearch').value);
  }
  changeValue() {
    this.paginationEvEmitter.clickPaginationTableEmitter(-1 * this.formTable.get('itemsPerPage').value);
  }
  clearSearch() {
    this.formTable.patchValue({
      nmSearch: null
    });
    this.paginationEvEmitter.clickPaginationTableEmitter(-1221);
  }
  setPageStart() {
    this.paginationEvEmitter.clickPaginationTableEmitter(-1210);
  }
  setPagePrev() {
    this.paginationEvEmitter.clickPaginationTableEmitter(-1209);
  }
  setPageNext() {
     this.paginationEvEmitter.clickPaginationTableEmitter(-1208);
  }
  setPageLast() {
    this.paginationEvEmitter.clickPaginationTableEmitter(-1207);
  }
  setPage(i) {
    this.paginationEvEmitter.clickPaginationTableEmitter(i);
  }

}
