package exchange.load;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Date;

@Value
@Builder
class LoadInfo {
    private String owner;
    private String from;
    private String to;
    private Date shouldBeDeliveredOn;
    private BigDecimal price;
}
