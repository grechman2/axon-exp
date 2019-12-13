package exchange.ui.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final CardSummaryService cardSummaryService;

//    @SubscribeMapping("/gift-cards")
//    public List<CardSummary> getAllGiftCards(){
//        return cardSummaryService.getCartSummaries(0, 10000, new CardSummaryFilter(""));
//    }


}
