package org.grechman.axonexp.ui.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.grechman.axonexp.query.CardSummary;
import org.grechman.axonexp.query.CardSummaryFilter;
import org.grechman.axonexp.query.CountChangedUpdate;
import org.grechman.axonexp.query.FetchCardSummariesQuery;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardSummaryService {

    private final QueryGateway queryGateway;
    private SubscriptionQueryResult<List<CardSummary>, CardSummary> fetchQueryResult;
    private SubscriptionQueryResult<Integer, CountChangedUpdate> countQueryResult;
    private CardSummaryFilter filter = new CardSummaryFilter("");

    public Mono<List<CardSummary>> getCartSummaries(int offSet, int limit, CardSummaryFilter filter) {
        FetchCardSummariesQuery fetchCardSummariesQuery =
                new FetchCardSummariesQuery(offSet, limit, filter);

        log.trace("submitting {}", fetchCardSummariesQuery);

        /*
         * Submitting our query as a subscriptionquery, specifying both the initially expected
         * response type (multiple CardSummaries) as wel as the expected type of the updates
         * (single CardSummary object). The result is a SubscriptionQueryResult which contains
         * a project reactor Mono for the initial response, and a Flux for the updates.
         */
        fetchQueryResult = queryGateway.subscriptionQuery(fetchCardSummariesQuery,
                ResponseTypes.multipleInstancesOf(CardSummary.class),
                ResponseTypes.instanceOf(CardSummary.class));

        /*
         * Subscribing to the updates before we get the initial results.
         */
        fetchQueryResult.updates().subscribe(
                cardSummary -> {
                    log.trace("processing query update for {}: {}", fetchCardSummariesQuery, cardSummary);

                    /* This is a Vaadin-specific call to update the UI as a result of data changes. */
                    //fireEvent(new DataChangeEvent.DataRefreshEvent<>(this, cardSummary));
                }
        );

//        Flux<CardSummary> cardSummaryFlux = Flux.fromStream(fetchQueryResult.initialResult().block().stream());

        /*
         * Returning the initial result.
         */
        return fetchQueryResult.initialResult();
    }

}
