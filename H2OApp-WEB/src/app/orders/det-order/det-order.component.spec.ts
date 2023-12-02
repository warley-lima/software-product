import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetOrderComponent } from './det-order.component';

describe('DetOrderComponent', () => {
  let component: DetOrderComponent;
  let fixture: ComponentFixture<DetOrderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetOrderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
