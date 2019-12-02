package org.grechman.axonexp.ui.api;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.grechman.axonexp.commands.IssueCommand;
import org.grechman.axonexp.commands.RedeemCommand;
import org.grechman.axonexp.query.CardSummary;
import org.grechman.axonexp.query.CardSummaryFilter;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(Api.GiftCard.BASE_GIFT_CARD)
public class GiftCardController {

    private final CommandGateway commandGateway;
    private final CardSummaryService cardSummaryService;

    public GiftCardController(CommandGateway commandGateway,
                              CardSummaryService cardSummaryService) {
        this.commandGateway = commandGateway;
        this.cardSummaryService = cardSummaryService;
    }


    @PostMapping(Api.GiftCard.ISSUE_GIFT_CARD)
    public void issueGiftCard(@RequestBody IssueCommand command) {
        commandGateway.sendAndWait(command);
    }

    @PutMapping(Api.GiftCard.REDEEM_GIFT_CARD)
    public void redeemGiftCard(@PathVariable String id, @PathVariable Integer amount) {
        commandGateway.sendAndWait(new RedeemCommand(id, amount));
    }

    @GetMapping("/")
    @CrossOrigin(origins = "http://localhost:4200")
    public Flux<CardSummary> getCardSummaryResult() {
        return cardSummaryService.getCartSummaries(0, 0, new CardSummaryFilter("test"));
//                return Flux
//                .range(0, 10000)
//                .map( index -> CardSummary
//                        .builder()
//                        .id("id"+index)
//                        .initialBalance(index)
//                        .remainingBalance(index)
//                        .build());

    }


}
