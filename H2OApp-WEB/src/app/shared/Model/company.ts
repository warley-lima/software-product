export class Company {
  public sc: string;
  public fn: string;
  public uf: string;
  public ad: string;
  public nu: string;
  public ba: string;
  public te: string;
  public ce: string;
  public re: string;
  public ma: string;
  public cnpj: string;
  public la: number;
  public lo: number;
   constructor(socialName: string,  fantasyName: string, uf: string,
    adress: string,  number: string, bairro: string, fone: string,  cep: string, responsavel: string,
    email: string, cnpj: string, latitude: number,  longitude: number) {
     this.sc = socialName;
     this.fn = fantasyName;
     this.uf = uf;
     this.ad = adress;
     this.nu = number;
     this.ba = bairro;
     this.te = fone;
     this.ce = cep;
     this.re = responsavel;
     this.ma = email;
     this.cnpj = cnpj;
     this.la = latitude;
     this.lo = longitude;
   }
}
