package org.grechman.axonexp.ui.api;

import lombok.RequiredArgsConstructor;
import org.grechman.axonexp.query.CardSummary;
import org.grechman.axonexp.query.CardSummaryFilter;
import org.grechman.axonexp.ui.api.CardSummaryService;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final CardSummaryService cardSummaryService;

//    @SubscribeMapping("/gift-cards")
//    public List<CardSummary> getAllGiftCards(){
//        return cardSummaryService.getCartSummaries(0, 10000, new CardSummaryFilter(""));
//    }


}
