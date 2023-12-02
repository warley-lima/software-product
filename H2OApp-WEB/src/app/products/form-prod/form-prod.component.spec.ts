import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormProdComponent } from './form-prod.component';

describe('FormProdComponent', () => {
  let component: FormProdComponent;
  let fixture: ComponentFixture<FormProdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormProdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormProdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
