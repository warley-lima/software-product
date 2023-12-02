export class Delivery {
  public id: number;
  public distance: number;
  public txDelivery: number;
  public idCompany: number;
   constructor(id: number, idCompany: number, distance: number, txDelivery: number) {
     this.id = id;
     this.distance = distance;
     this.txDelivery = txDelivery;
     this.idCompany = idCompany;
   }
}
