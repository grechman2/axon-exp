import { TestBed } from '@angular/core/testing';

import { GiftCardsService } from './gift-cards.service';

describe('GiftCardsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GiftCardsService = TestBed.get(GiftCardsService);
    expect(service).toBeTruthy();
  });
});
