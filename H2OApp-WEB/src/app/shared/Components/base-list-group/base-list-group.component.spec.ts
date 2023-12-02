import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BaseListGroupComponent } from './base-list-group.component';

describe('BaseListGroupComponent', () => {
  let component: BaseListGroupComponent;
  let fixture: ComponentFixture<BaseListGroupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BaseListGroupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BaseListGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
