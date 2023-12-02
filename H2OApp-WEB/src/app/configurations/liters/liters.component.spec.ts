import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LitersComponent } from './liters.component';

describe('LitersComponent', () => {
  let component: LitersComponent;
  let fixture: ComponentFixture<LitersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LitersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LitersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
