import { Brand } from './../../Model/brand';
import { Component, Input, forwardRef } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

const INPUT_FIELD_VALUE_ACCESSOR: any = {
  provide: NG_VALUE_ACCESSOR,
  useExisting: forwardRef(() => SelectFieldComponent),
  multi: true
};
@Component({
  selector: 'app-select-field',
  templateUrl: './select-field.component.html',
  styleUrls: ['./select-field.component.scss'],
  providers: [INPUT_FIELD_VALUE_ACCESSOR]
})
export class SelectFieldComponent implements ControlValueAccessor {

  constructor() { }

  @Input() classeCss;
  @Input() id: string;
  @Input() label: string;
  @Input() control;
  @Input() isReadOnly = false;
  @Input() objWithValues: any[];
  private innerValue: any;

  get value() {
    return this.innerValue;
  }

  set value(v: any) {
    if (v !== this.innerValue) {
      this.innerValue = v;
      this.onChangeCb(v);
    }
  }

  onChangeCb: (_: any) => void = () => {};
  onTouchedCb: (_: any) => void = () => {};

  writeValue(v: any): void {
    this.value = v;
  }

  registerOnChange(fn: any): void {
    this.onChangeCb = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouchedCb = fn;
  }

  setDisabledState?(isDisabled: boolean): void {
    this.isReadOnly = isDisabled;
  }

  compareObjWithValues(obj1, obj2) {
   return obj1 && obj2 ? (obj1.nm === obj2.nm && obj1.id === obj2.id) : obj1 === obj2;
  }

}
