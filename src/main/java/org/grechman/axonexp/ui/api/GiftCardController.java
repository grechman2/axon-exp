package org.grechman.axonexp.ui.api;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.grechman.axonexp.commands.IssueCommand;
import org.grechman.axonexp.commands.RedeemCommand;
import org.grechman.axonexp.query.CardSummary;
import org.grechman.axonexp.query.CardSummaryFilter;
import org.grechman.axonexp.query.FetchCardSummariesQuery;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
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


    @PostMapping(Api.GiftCard.ISSUE_GIFT_CARD)
    public void issueGiftCard(@RequestBody IssueCommand command) {
        commandGateway.sendAndWait(command);
    }

    @PutMapping(Api.GiftCard.REDEEM_GIFT_CARD)
    public void redeemGiftCard(@PathVariable String id, @PathVariable Integer amount) {
        commandGateway.sendAndWait(new RedeemCommand(id, amount));
    }

//    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    @CrossOrigin(origins = "http://localhost:4200")
//    public Flux<CardSummary> getEvents(
//            @RequestParam("idStartsWith") String idStartsWith,
//            HttpServletResponse response) {
//        // return toSSEFluxForInitialCollection(response, subscriptionQueryResult(queryGateway, idStartsWith));
//
//
//    }

    @GetMapping(value = "/", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public Flux<ServerSentEvent<CardSummary>> getCardSummaryResult(
            @RequestParam("idStartsWith") String idStartsWith,
            HttpServletResponse response) {
        return toSSEFluxForInitialCollection(response, subscriptionQueryResult(queryGateway, idStartsWith));


    }

    static SubscriptionQueryResult<List<CardSummary>, CardSummary> subscriptionQueryResult(
            QueryGateway queryGateway, String idStartsWith
    ) {

        SubscriptionQueryResult<List<CardSummary>, CardSummary> queryResult = queryGateway
                .subscriptionQuery(
                        new FetchCardSummariesQuery(0,0, new CardSummaryFilter(idStartsWith)),
                        ResponseTypes.multipleInstancesOf(CardSummary.class),
                        ResponseTypes.instanceOf(CardSummary.class));
        return queryResult;
    }

    @Override
    public Logger logger() {
        return log;
    }
}