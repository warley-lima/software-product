import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemWIconComponent } from './item-w-icon.component';

describe('ItemWIconComponent', () => {
  let component: ItemWIconComponent;
  let fixture: ComponentFixture<ItemWIconComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItemWIconComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemWIconComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
