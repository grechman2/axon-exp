package exchange.load;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Date;

@Value
@Builder
@Getter
public class LoadInfo2 {
    private String owner;
    private String from;
    private String to;
    private Date shouldBeDeliveredOn;
    private BigDecimal price;
    private LoadStatus loadStatus;
}
