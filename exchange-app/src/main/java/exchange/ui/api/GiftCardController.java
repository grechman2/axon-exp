package exchange.ui.api;

import exchange.commands.IssueCommand;
import exchange.commands.RedeemCommand;
import exchange.query.CardSummary;
import exchange.query.CardSummaryFilter;
import exchange.query.FetchCardSummariesQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(Api.GiftCard.BASE_GIFT_CARD)
public class GiftCardController implements ServerSentEventProducerSupport {

    private final CommandGateway commandGateway;
    private final CardSummaryService cardSummaryService;
    private final QueryGateway queryGateway;

    public GiftCardController(CommandGateway commandGateway,
                              CardSummaryService cardSummaryService,
                              QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.cardSummaryService = cardSummaryService;
        this.queryGateway = queryGateway;
    }

    static SubscriptionQueryResult<List<CardSummary>, CardSummary> subscriptionQueryResult(
            QueryGateway queryGateway, String idStartsWith
    ) {

        SubscriptionQueryResult<List<CardSummary>, CardSummary> queryResult = queryGateway
                .subscriptionQuery(
                        new FetchCardSummariesQuery(0, 1000, new CardSummaryFilter(idStartsWith)),
                        ResponseTypes.multipleInstancesOf(CardSummary.class),
                        ResponseTypes.instanceOf(CardSummary.class));
        return queryResult;
    }

    @PostMapping(Api.GiftCard.ISSUE_GIFT_CARD)
    public void issueGiftCard(@RequestBody IssueCommand command) {
        commandGateway.sendAndWait(command);
    }

    @PutMapping(Api.GiftCard.REDEEM_GIFT_CARD)
    public void redeemGiftCard(@PathVariable String id, @PathVariable Integer amount) {
        commandGateway.sendAndWait(new RedeemCommand(id, amount));
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public List<CardSummary> getCardSummaryResult() {
        return cardSummaryService.getCartSummaries(0,100000, new CardSummaryFilter(""));
    }

    @Override
    public Logger logger() {
        return log;
    }
}