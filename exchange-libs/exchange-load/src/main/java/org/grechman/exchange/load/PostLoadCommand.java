package org.grechman.exchange.load;

import lombok.Value;

import java.math.BigDecimal;
import java.util.Date;


@Value
public class PostLoadCommand extends AbstractLoadCommand {

    private PostLoadDetails postLoadDetails;

    public PostLoadCommand(PostLoadDetails postLoadDetails) {
        this.postLoadDetails = postLoadDetails;
    }

    @Value
    public static class PostLoadDetails{
        private String owner;
        private String from;
        private String to;
        private Date shouldBeDeliveredOn;
        private BigDecimal price;
    }

}
