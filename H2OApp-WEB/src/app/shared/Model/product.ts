export class Product {
  public nm: string;
  public lt: number;
  public vl: number;
  public idB: number;
  public idP: number;
   constructor(idProd: number, name: string,  litters: number, value: number, idBrand: number) {
     this.nm = name;
     this.idP = idProd;
     this.lt = litters;
     this.vl = value;
     this.idB = idBrand;
   }
}
