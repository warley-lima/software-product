import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
@Injectable()
export class Utils {

  constructor() { }

   verificaValidacoesForm(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(campo => {
      console.log(campo);
      const controle = formGroup.get(campo);
      controle.markAsDirty();
      if (controle instanceof FormGroup) {
        this.verificaValidacoesForm(controle);
      }
    });
  }
  formatRs(valor: string) {
    if (valor !== null ) {
     return valor.replace('R$', '');
    } else {
      return null;
    }
  }
  formatStringToMoney(valor: string) {
    if (valor !== null && valor.search(',') !== -1 ) {
      let v = valor.replace('.' , '');
      v = v.replace('R$', '');
      v = v.replace(',', '.');
      return  Number(v);
    } else {
      return Number(valor);
    }
  }
  formatNumberToMoney(valor: number) {
    if (valor !== null ) {
      let v = valor.toFixed(2);
      v = v.replace('.', ',');
     return  v;
    } else {
      return valor;
    }
  }
  formatDateTime(time: string, date: string) {
    if (date !== null && time !== null) {
       date = date.replace('-', '/');
       time = time.replace('-', ':');
       return time.concat(' ').concat(date);
    } else {
      return null;
    }
  }
  formatTime(time: string) {
    if (time !== null) {
       return time.substring(0, 8);
    } else {
      return null;
    }
  }
  formatDate(date: string) {
    if (date !== null) {
       return date.substring(9, 19);
    } else {
      return null;
    }
  }
  formatPayment(v: string) {
    if (v !== null) {
      let ret = null;
      switch (v) {
          case 'D':
            ret = 'Débito';
          break;
          case 'M':
            ret = 'Dinheiro';
          break;
          case 'C':
            ret = 'Crédito';
          break;
      }
      return ret;
    } else {
      return null;
    }
  }
  resetar(formulario: FormGroup) {
    formulario.reset();
  }
  verificaValidTouched(formulario: FormGroup, campo: string) {
    return (
      !formulario.get(campo).valid &&
      (formulario.get(campo).touched || formulario.get(campo).dirty)
    );
  }
  verificaEmailInvalido(formulario: FormGroup) {
    const campoEmail = formulario.get('email');
    if (campoEmail.errors) {
      return campoEmail.errors['email'] && campoEmail.touched;
    }
  }
  aplicaCssErro(formulario: FormGroup, campo: string) {
    return {
      'has-error': this.verificaValidTouched(formulario, campo),
      'has-feedback': this.verificaValidTouched(formulario, campo)
    };
  }

}
