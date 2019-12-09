package org.grechman.exchange.load;

import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class PostLoadCommand extends AbstractLoadCommand {
    private PostLoadDetails postLoadDetails = null;

    public PostLoadCommand() {
        super(new Load.LoadId());
    }

    public PostLoadCommand(PostLoadDetails postLoadDetails){
        super(new Load.LoadId());
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
