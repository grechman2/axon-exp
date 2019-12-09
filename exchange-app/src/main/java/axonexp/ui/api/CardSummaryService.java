package axonexp.ui.api;

import axonexp.query.CardSummary;
import axonexp.query.CardSummaryFilter;
import axonexp.query.FetchCardSummariesQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardSummaryService {

    private final QueryGateway queryGateway;
    private SubscriptionQueryResult<List<CardSummary>, CardSummary> fetchQueryResult;

    public List<CardSummary> getCartSummaries(int offSet, int limit, CardSummaryFilter filter) {
        FetchCardSummariesQuery fetchCardSummariesQuery =
                new FetchCardSummariesQuery(offSet, limit, filter);

        log.trace("submitting {}", fetchCardSummariesQuery);

        fetchQueryResult = queryGateway.subscriptionQuery(fetchCardSummariesQuery,
                ResponseTypes.multipleInstancesOf(CardSummary.class),
                ResponseTypes.instanceOf(CardSummary.class));

        return fetchQueryResult.initialResult().block();
    }

}
