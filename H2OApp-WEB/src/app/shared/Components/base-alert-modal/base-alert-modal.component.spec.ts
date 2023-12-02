import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BaseAlertModalComponent } from './base-alert-modal.component';

describe('BaseAlertModalComponent', () => {
  let component: BaseAlertModalComponent;
  let fixture: ComponentFixture<BaseAlertModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BaseAlertModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BaseAlertModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
