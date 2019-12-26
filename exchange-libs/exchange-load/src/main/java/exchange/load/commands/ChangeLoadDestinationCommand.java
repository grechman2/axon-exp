package exchange.load.commands;

import exchange.load.LoadId;
import lombok.Data;


@Data
public class ChangeLoadDestinationCommand extends AbstractLoadCommand {

    private String to;

    public ChangeLoadDestinationCommand() {
        super(new LoadId());
    }

    public ChangeLoadDestinationCommand(String loadId, String to) {
        super(new LoadId(loadId));
        this.to = to;
    }
}
