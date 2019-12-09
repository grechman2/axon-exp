import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HttpClientModule} from "@angular/common/http";
import { GiftCardsListComponent } from './gift-cards-list/gift-cards-list.component';
import {StompConfig, StompService} from "@stomp/ng2-stompjs";
import {environment} from "../environments/environment";

const stompConfig: StompConfig = {
  // Which server?
  url: environment.webSocketURL,

  // Headers
  // Typical keys: login, passcode, host
  headers: {
    login: 'guest',
    passcode: 'guest'
  },

  // How often to heartbeat?
  // Interval in milliseconds, set to 0 to disable
  heartbeat_in: 0, // Typical value 0 - disabled
  heartbeat_out: 20000, // Typical value 20000 - every 20 seconds
  // Wait in milliseconds before attempting auto reconnect
  // Set to 0 to disable
  // Typical value 5000 (5 seconds)
  reconnect_delay: 5000,

  // Will log diagnostics on console
  debug: true
};

@NgModule({
  declarations: [
    AppComponent,
    GiftCardsListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule
  ],
  providers: [
    StompService,
    {
      provide: StompConfig,
      useValue: stompConfig
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
