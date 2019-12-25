package exchange.load.commands;

import exchange.load.Load;
import exchange.load.commands.AbstractLoadCommand;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class PostLoadCommand extends AbstractLoadCommand {

    private String owner;
    private String from;
    private String to;
    private Date shouldBeDeliveredOn;
    private BigDecimal price;

    public PostLoadCommand() {
        super(new Load.LoadId());
    }

    public PostLoadCommand(String owner,
                           String from,
                           String to,
                           Date shouldBeDeliveredOn,
                           BigDecimal price) {
        super(new Load.LoadId());
        this.owner = owner;
        this.from = from;
        this.to = to;
        this.shouldBeDeliveredOn = shouldBeDeliveredOn;
        this.price = price;
    }
}
