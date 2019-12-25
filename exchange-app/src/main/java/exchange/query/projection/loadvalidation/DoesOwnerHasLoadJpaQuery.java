package exchange.query.projection.loadvalidation;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DoesOwnerHasLoadJpaQuery {
    private String owner;
}
