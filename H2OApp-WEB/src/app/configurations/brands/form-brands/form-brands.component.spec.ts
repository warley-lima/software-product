import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormBrandsComponent } from './form-brands.component';

describe('FormBrandsComponent', () => {
  let component: FormBrandsComponent;
  let fixture: ComponentFixture<FormBrandsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormBrandsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormBrandsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
