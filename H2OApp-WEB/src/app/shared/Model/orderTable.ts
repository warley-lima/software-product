export class OrderTable {
  public nm: string;
  public fn: string;
  public st: string;
  public dtp: string;
  public vt: number;
  public idO: number;
   constructor(idOrder: number, nm: string, fn: string, valor: number, dtp: string, st: string) {
     this.idO = idOrder;
     this.vt = valor;
     this.nm = nm;
     this.fn = fn;
     this.dtp = dtp;
     this.st = st;
   }
}
