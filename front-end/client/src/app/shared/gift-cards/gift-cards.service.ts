import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {GiftCardSummary} from "../model/GiftCardSummary";

@Injectable({
  providedIn: 'root'
})
export class GiftCardsService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<GiftCardSummary[]>{
    const url: string = 'http://localhost:8080/api/gift-cards/';
    return this.http.get(url).map(this.extractListData);
  }

  private extractListData(res) {
    console.log(res);
    return res;
  }

}
