export class EnderecoERP {
  public bairro: string;
  public ce: string;
  public cidade: string;
  public complemento: string;
  public complemento2: string;
  public end: string;
  public uf: string;
  public id: number;
   constructor(
    bairro: string, cep: string, cidade: string, complemento: string , complemento2: string, id: number,
     end: string, uf: string) {
     this.id = id;
     this.bairro = bairro;
     this.ce = cep;
     this.cidade = cidade;
     this.complemento = complemento;
     this.complemento2 = complemento2;
     this.end = end;
     this.uf = uf;
   }
}
