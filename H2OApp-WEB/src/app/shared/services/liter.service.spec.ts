import { TestBed } from '@angular/core/testing';

import { LiterService } from './liter.service';

describe('LiterService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LiterService = TestBed.get(LiterService);
    expect(service).toBeTruthy();
  });
});
