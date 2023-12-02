import { TestBed } from '@angular/core/testing';

import { SpinnerLoadService } from './spinner-load.service';

describe('SpinnerLoadService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SpinnerLoadService = TestBed.get(SpinnerLoadService);
    expect(service).toBeTruthy();
  });
});
