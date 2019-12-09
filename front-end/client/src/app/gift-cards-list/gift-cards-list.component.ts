import { Component, OnInit } from '@angular/core';
import {GiftCardsService} from "../shared/gift-cards/gift-cards.service";
import {Observable} from 'rxjs/Rx'
import {GiftCardSummary} from "../shared/model/GiftCardSummary";
import {StompService} from "@stomp/ng2-stompjs";

@Component({
  selector: 'app-gift-cards-list',
  templateUrl: './gift-cards-list.component.html',
  styleUrls: ['./gift-cards-list.component.css']
})
export class GiftCardsListComponent implements OnInit {
  giftCards$: Observable<GiftCardSummary[]>;

  constructor(private giftCardService: GiftCardsService, private stompService: StompService) { }

  ngOnInit() {
    const dataChanges = [
      this.stompService.subscribe("/topic/cardsummary.updates")
    ];

    this.giftCards$ = Observable
      .merge(...dataChanges)
      .startWith(null)
      .switchMap(event => this.giftCardService.getAll())
  }

}
