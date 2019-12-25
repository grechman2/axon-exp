package exchange.load.commands;

import exchange.load.Load;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class ChangeLoadDestinationCommand extends AbstractLoadCommand {

    private String to;

    public ChangeLoadDestinationCommand() {
        super(new Load.LoadId());
    }

    public ChangeLoadDestinationCommand(String loadId, String to) {
        super(new Load.LoadId(loadId));
        this.to = to;
    }
}
