package axonexp.exchange.query.projection;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DoesOwnerHasLoadQuery {
    private String owner;
}
