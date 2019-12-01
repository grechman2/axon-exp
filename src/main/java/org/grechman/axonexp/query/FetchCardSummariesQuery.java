package org.grechman.axonexp.query;

import lombok.Value;

@Value
public class FetchCardSummariesQuery {

    private int offSet;
    private int limit;
    private CardSummaryFilter filter;
}
