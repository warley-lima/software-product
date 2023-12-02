import { BaseAlertMsgUtils } from './../shared/Utils/base-alert-msg-utils';
import {Component, OnInit, OnDestroy} from '@angular/core';
import {FormBuilder,  FormControl } from '@angular/forms';
import { Subscription} from 'rxjs';

// import { FormValidations } from '../shared/Utils/form-validations';
import { ToolbarUtils } from '../shared/Utils/toolbar-utils';
import { ProductService } from '../shared/services/product.service';
import { Product } from '../shared/Model/product';
import { BrandService } from '../shared/services/brand.service';
import { Brand } from '../shared/Model/brand';
import { IBaseAlertModal } from '../shared/Actions/IBaseAlertModal';
import { TableUtils } from '../shared/Utils/table-utils';
declare let $: any;
@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit, OnDestroy, IBaseAlertModal {
  private inscToolBarEmiter: Subscription;
  private inscProductService: Subscription;
  private inscBrandService: Subscription;
  private inscTablePagEmiter: Subscription;
  private inscSearchTableEmiter: Subscription;
  private idProd: number;
  titleModal: string;
  bodyModal: string;
  showModal: string;
  display = 'none';
  prods: Product[];
  first: boolean;
  last: boolean;
  page: number;
  numberOfElements: number;
  size: number;
  sort: any;
  totalElements: number;
  pages: Array<number>;
  limPages: string;
  brands: Brand[];
  modalVisible = false;
  isSeacrh: boolean;
  nmSearch: string;
  tbNameCols = ['Nome', 'Marca', 'Litros', 'Valor', 'Editar', 'Excluir'];

  constructor(
    private prodService: ProductService,
    private toolBarEvEmitter: ToolbarUtils,
    private tabPagEvEmitter: TableUtils,
   // private formBuilder: FormBuilder,
    private alertMsgEvEmitter: BaseAlertMsgUtils,
    private brandService: BrandService
  ) {}

  createTable() {}
  setPage(i) {
    this.page = i;
    if (!this.isSeacrh) {
      this.getProducts();
    } else {
      this.searchProducts();
    }
  }
  setPageNext() {
    this.page++;
    if (!this.isSeacrh) {
      this.getProducts();
    } else {
      this.searchProducts();
    }
  }
  setPagePrev() {
    this.page--;
    if (!this.isSeacrh) {
      this.getProducts();
    } else {
      this.searchProducts();
    }
  }
  setPageLast() {
    this.page = this.pages.length - 1;
    if (!this.isSeacrh) {
      this.getProducts();
    } else {
      this.searchProducts();
    }
  }
  setPageStart() {
    this.page = 0;
    if (!this.isSeacrh) {
      this.getProducts();
    } else {
      this.searchProducts();
    }
  }
  clearSearch() {
    this.isSeacrh = false;
    this.nmSearch = null;
    this.page = 0;
    this.getProducts();
  }
  changeValue() {
    this.page = 0;
    if (!this.isSeacrh) {
      this.getProducts();
    } else {
      this.searchProducts();
    }
  }
  clickBtnNo() {
    $('#myModalAlert').modal('hide');
  }
  clickBtnYes() {
    $('#myModalAlert').modal('hide');
    this.showSpinner();
    this.inscProductService = this.prodService
      .delProduct(this.idProd)
      .subscribe(
        p => {
          if ('S;' === p['ret']) {
            if (this.page >= 1) {
              this.page--;
            }
            this.showAlertMsg('Exclusão realizada com Sucesso! :)', 'alert-success');
            this.getProducts();
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
  updateProd(p: Product) {
    this.titleModal = 'Editar Produto';
    this.prodService.updateProduct(p);
    $('#myModal').modal('show');
  }
  deleteProd(id) {
    this.titleModal = 'Excluir Produto';
    this.bodyModal =
      'Atenção: Excluir o produto, irá apagar todos os dados referentes ao produto. Deseja continuar?';
    $('#myModalAlert').modal('show');
    this.idProd = id;
  }
  getProducts() {
    this.prodService.getProducts(this.page.toString(), this.limPages).subscribe(
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
  getBrands() {
    /* this.inscBrandService = this.brandService.getBrandsLocal().subscribe(
      b => {
       if (null !== b) {
       this.brands = b;
       this.brandService.getBrandsLocal2(this.brands);
       }
     },
     error => {
       this.showAlertMsg('Falha ao pegar as marcas!', 'alert-danger');
       console.log(error.error.message);
     }
    );*/
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
          });
     }
    /* if (undefined === this.brands || null === this.brands) {
          this.inscBrandService = this.brandService.getAllBrandsByCompany().subscribe(
            b => {
            if (null !== b) {
            this.brands = b;
            this.brandService.getBrandsLocal2(this.brands);
            }
          },
          error => {
            this.showAlertMsg('Falha ao pegar as marcas!', 'alert-danger');
            console.log(error.error.message);
          }
        );
     } */
  }
  searchProducts() {
      this.prodService.searchProducts(this.page.toString(), this.limPages,
      this.nmSearch).subscribe(p => {
        if (null !== p) {
            this.prods = p['content'];
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

  getNameBrand(id) {
    if (id !== undefined) {
      return this.brandService.getNameBrand(id);
    }
  }

  showSpinner() {}

  hideSpinner() {}

  ngOnInit() {
    this.onCloseHandled();
    this.showModal = 'modal fade';
    this.page = 0;
    this.limPages = '5';
    this.nmSearch = null;
    this.getProducts();
    this.getBrands();
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
      this.searchProducts();
    });



    this.inscProductService = this.prodService.prodRefreshEmitter.subscribe(
      p => {
        if (true === p) {
          this.getProducts();
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
   // this.inscBrandService.unsubscribe();
    this.inscProductService.unsubscribe();
    this.inscTablePagEmiter.unsubscribe();
    this.inscSearchTableEmiter.unsubscribe();
  }

  teste2() {
   // console.log('Componente FILHO CARAIO PRODUTOS');
  }
  showAlertMsg(body: string, classAlert: string) {
    this.alertMsgEvEmitter.alertBodyMsgEmitt.emit(body);
    this.alertMsgEvEmitter.alertClassCssMsgEmitt.emit(classAlert);
    this.alertMsgEvEmitter.alertMsgEmitt.emit(true);
  }
  onVoted(b: string) {
    this.titleModal = 'Adicionar Novo Produto';
    this.prodService.emittProduct();
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
