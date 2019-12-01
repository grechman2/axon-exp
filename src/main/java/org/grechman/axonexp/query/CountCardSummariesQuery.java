package org.grechman.axonexp.query;

import lombok.Value;

@Value
public class CountCardSummariesQuery {

    CardSummaryFilter filter;
}
