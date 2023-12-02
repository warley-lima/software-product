export class Coupon {
  public vc: string;
  public dti: string;
  public dtf: string;
  public vd: string;
  public idC: number;
   constructor(idCupom: number, valor: string,  dti: string, dtf: string, vd: string) {
     this.vc = valor;
     this.idC = idCupom;
     this.dti = dti;
     this.dtf = dtf;
     this.vd = vd;
   }
}
