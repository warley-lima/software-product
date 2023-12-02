import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormUpdOrderComponent } from './form-upd-order.component';

describe('FormUpdOrderComponent', () => {
  let component: FormUpdOrderComponent;
  let fixture: ComponentFixture<FormUpdOrderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormUpdOrderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormUpdOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
