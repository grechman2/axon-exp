package org.grechman.axonexp.ui.api;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.grechman.axonexp.commands.IssueCommand;
import org.grechman.axonexp.commands.RedeemCommand;
import org.grechman.axonexp.query.CardSummary;
import org.grechman.axonexp.query.CardSummaryFilter;
import org.springframework.web.bind.annotation.*;
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
    public Mono<List<CardSummary>> getCardSummaryResult(
            @RequestParam("idStartsWith") String idStartsWith) {
        return cardSummaryService.getCartSummaries(0, 0, new CardSummaryFilter(idStartsWith));
    }


}
