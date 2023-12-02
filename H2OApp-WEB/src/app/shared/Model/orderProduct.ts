export class OrderProduct {
  public nameProduct: string;
  public liters: number;
  public quantidade: number;
  public idBrand: number;
  public precoUnitarioProduto: number;
  public idCompany: number;
  public idProduct: number;
   constructor(idCompany: number, idProduct: number, precoUnitarioProduto: number, idBrand: number,
    quantidade: number, liters: number, nameProduct: string) {
      this.nameProduct = nameProduct;
      this.liters = liters;
      this.quantidade = quantidade;
      this.idBrand = idBrand;
      this.precoUnitarioProduto = precoUnitarioProduto;
      this.idCompany = idCompany;
      this.idProduct = idProduct;
    }
}
