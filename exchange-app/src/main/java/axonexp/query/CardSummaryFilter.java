package axonexp.query;

import com.sun.istack.NotNull;
import lombok.Value;
import lombok.With;

@Value
@With
public class CardSummaryFilter {

    @NotNull
    private String idStartsWith;
}
