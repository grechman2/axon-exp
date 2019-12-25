import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GiftCardsListComponent } from './gift-cards-list.component';

describe('GiftCardsListComponent', () => {
  let component: GiftCardsListComponent;
  let fixture: ComponentFixture<GiftCardsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GiftCardsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GiftCardsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
