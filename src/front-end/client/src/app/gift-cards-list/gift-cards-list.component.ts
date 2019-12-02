import { Component, OnInit } from '@angular/core';
import {GiftCardsService} from "../shared/gift-cards/gift-cards.service";

@Component({
  selector: 'app-gift-cards-list',
  templateUrl: './gift-cards-list.component.html',
  styleUrls: ['./gift-cards-list.component.css']
})
export class GiftCardsListComponent implements OnInit {
  giftCards: Array<any>;

  constructor(private giftCardService: GiftCardsService) { }

  ngOnInit() {
    this.giftCardService.getAll().subscribe(data => {
       this.giftCards = data;
    })
  }

}
