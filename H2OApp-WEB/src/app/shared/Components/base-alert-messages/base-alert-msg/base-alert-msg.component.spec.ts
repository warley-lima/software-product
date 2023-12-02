import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BaseAlertMsgComponent } from './base-alert-msg.component';

describe('BaseAlertMsgComponent', () => {
  let component: BaseAlertMsgComponent;
  let fixture: ComponentFixture<BaseAlertMsgComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BaseAlertMsgComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BaseAlertMsgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
