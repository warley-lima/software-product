export class UserServer {
  public nm: string;
  public em: string;
  public pa: string;
  public idU: number;
   constructor(idProd: number, name: string,  email: string, pass: string) {
     this.nm = name;
     this.idU = idProd;
     this.em = email;
     this.pa = pass;
   }
}
