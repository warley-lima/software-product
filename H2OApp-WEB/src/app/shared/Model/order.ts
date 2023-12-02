import { OrderProduct } from './orderProduct';

export class Order {
  public nm: string;
  public fn: string;
  public nc: string;
  public co: string;
  public ad: string;
  public ba: string;
  public ci: string;
  public uf: string;
  public cd: string;
  public fp: string;
  public tr: string;
  public de: string;
  public st: string;
  public stn: string;
  public dt: string;
  public vd: string;
  public vt: number;
  public idO: number;
  public idD: number;
  public tx: number;
  public lstProd: OrderProduct[];
   constructor(idO: number, idDist: number, valorTotal: number, tx: number,
    name: string, fone: string, dtp: string, st: string , stn: string, numberHouse: string,
    complement: string, adress: string, bairro: string, city: string, uf: string,
    cupomDesconto: string, formaPagamento: string, troco: string, dicasEntrega: string,
    valorDesconto: string,
    lstProduct: OrderProduct[]) {
     this.idO = idO;
     this.idD = idDist;
     this.vt = valorTotal;
     this.tx = tx;
     this.nm = name;
     this.fn = fone;
     this.dt = dtp;
     this.st = st;
     this.stn = stn;
     this.nc = numberHouse;
     this.co = complement;
     this.ad = adress;
     this.ba = bairro;
     this.ci = city;
     this.uf = uf;
     this.cd = cupomDesconto;
     this.fp = formaPagamento;
     this.tr = troco;
     this.de = dicasEntrega;
     this.vd = valorDesconto	;
     this.lstProd = lstProduct;
   }
}
