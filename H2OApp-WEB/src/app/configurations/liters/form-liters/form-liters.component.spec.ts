import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormLitersComponent } from './form-liters.component';

describe('FormLitersComponent', () => {
  let component: FormLitersComponent;
  let fixture: ComponentFixture<FormLitersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormLitersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormLitersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
