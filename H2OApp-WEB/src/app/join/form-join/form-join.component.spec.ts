import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormJoinComponent } from './form-join.component';

describe('FormJoinComponent', () => {
  let component: FormJoinComponent;
  let fixture: ComponentFixture<FormJoinComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormJoinComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormJoinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
