package exchange.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PostLoadDto {

    private String owner;
    private String from;
    private String to;
    private Date shouldBeDeliveredOn;
    private BigDecimal price;
}
