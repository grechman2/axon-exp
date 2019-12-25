import { Injectable } from '@angular/core';
import {PostLoadCommand} from "./model/PostLoadCommand";
import {HttpClient} from "@angular/common/http";
import {Observable, Subscriber} from "rxjs";
import {PartialObserver} from "rxjs/src/internal/types";

@Injectable({
  providedIn: 'root'
})
export class LoadService {

  constructor(private httpClient: HttpClient) {
  }

  public postLoad(command: PostLoadCommand){
      this.httpClient
        .post("http://localhost:8080/api/loads",new CreatePostLoadRequest(command))
        .subscribe({
          public void onNext()
        })

  }
}

export class CreatePostLoadRequest {
  postLoadDetails: PostLoadDetails;
  constructor(command: PostLoadCommand){
    this.postLoadDetails = new PostLoadDetails();
    this.postLoadDetails.from=command.from;
    this.postLoadDetails.owner=command.owner;
    this.postLoadDetails.price=command.price;
    this.postLoadDetails.to=command.to;
  }
}

export class PostLoadDetails{
  owner: string;
  from: string;
  to: string;
  price: number;
}
